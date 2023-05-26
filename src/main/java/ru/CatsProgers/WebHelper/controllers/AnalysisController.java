package ru.CatsProgers.WebHelper.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.CatsProgers.WebHelper.models.AnalysisResult;
import ru.CatsProgers.WebHelper.models.DoctorConsultation;
import ru.CatsProgers.WebHelper.models.StandardOfMedicalCare;
import ru.CatsProgers.WebHelper.services.AnalysisResultService;
import ru.CatsProgers.WebHelper.services.StandardService;
import ru.CatsProgers.WebHelper.services.ConsultationService;
import ru.CatsProgers.WebHelper.utils.ConsultationErrorResponse;
import ru.CatsProgers.WebHelper.utils.ConsultationNotCreatedException;

import java.util.List;

@Controller
@RequestMapping("/assistant/api")
public class AnalysisController {
    private final StandardService standardService;
    private final ConsultationService consultationService;
    private final AnalysisResultService analysisResultService;
    @Autowired
    public AnalysisController(StandardService standardService, ConsultationService consultationService, AnalysisResultService analysisResultService) {
        this.standardService = standardService;
        this.consultationService = consultationService;
        this.analysisResultService = analysisResultService;
    }
    @GetMapping("/firstpage")
    public ResponseEntity<HttpStatus> getFirstPage(){
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping("/{id}")
    public StandardOfMedicalCare getStandard(@PathVariable("id") int id){
        return standardService.getStandardByConsultationId(id);
    }
    @PostMapping
    public String createConsultation(
            @RequestBody @Valid DoctorConsultation consultation, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMessage
                            .append(error.getField())
                            .append(" - ")
                            .append(error.getDefaultMessage())
                            .append(";");
            }
            throw new ConsultationNotCreatedException(errorMessage.toString());
        }
        consultationService.saveConsultation(consultation);
        analysisResultService.saveNewAnalysisEntity(consultation);
        return "redirect:/result";
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
