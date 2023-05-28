package ru.CatsProgers.WebHelper.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.CatsProgers.WebHelper.models.AnalysisResult;
import ru.CatsProgers.WebHelper.models.Patient;
import ru.CatsProgers.WebHelper.services.AnalysisResultService;
import ru.CatsProgers.WebHelper.services.ExcelParserService;
import ru.CatsProgers.WebHelper.utils.ConsultationErrorResponse;
import ru.CatsProgers.WebHelper.utils.ConsultationNotCreatedException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assistant/firstpage")
public class AnalysisController {
    @Value(value = "${upload.path}")
    private String path;
    private final AnalysisResultService analysisResultService;
    private final ExcelParserService excelParserService;
    @Autowired
    public AnalysisController(AnalysisResultService analysisResultService, ExcelParserService excelParserService) {
        this.analysisResultService = analysisResultService;
        this.excelParserService = excelParserService;
    }
    @GetMapping
    public ResponseEntity<HttpStatus> getFirstPage(){
        analysisResultService.removeLastAnalysis();
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping
    public void postConsultation(@RequestParam("file") MultipartFile file) {
        List<Patient> patients = new ArrayList<>();
        if (file != null) {
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            try {
                File toFile = new File(path + "/" + resultFileName);
                file.transferTo(toFile);
                List<String> cellTexts = ExcelParserService.readExcel(toFile.getPath());
                excelParserService.setValuesInFields(cellTexts);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @ExceptionHandler
    private ResponseEntity<ConsultationErrorResponse> handleException(ConsultationNotCreatedException e){
        ConsultationErrorResponse consultationErrorResponse = new ConsultationErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(consultationErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/result")
    private AnalysisResult getResult(){
        return analysisResultService.getResult();
    }
}
