package ru.CatsProgers.WebHelper.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.CatsProgers.WebHelper.models.AnalysisResult;
import ru.CatsProgers.WebHelper.models.Consultation;
import ru.CatsProgers.WebHelper.services.AnalysisResultService;
import ru.CatsProgers.WebHelper.services.ConsultationService;
import ru.CatsProgers.WebHelper.utils.ConsultationErrorResponse;
import ru.CatsProgers.WebHelper.utils.ConsultationNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/assistant/firstpage")
public class AnalysisController {
    private final ConsultationService consultationService;
    private final AnalysisResultService analysisResultService;
    @Autowired
    public AnalysisController(ConsultationService consultationService, AnalysisResultService analysisResultService) {
        this.consultationService = consultationService;
        this.analysisResultService = analysisResultService;
    }
    @GetMapping
    public ResponseEntity<HttpStatus> getFirstPage(){
        analysisResultService.removeLastAnalysis();
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> createConsultation(
            @RequestBody @Valid Consultation consultation, BindingResult bindingResult){
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
        return ResponseEntity.ok(HttpStatus.OK);
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
