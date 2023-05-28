package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "analysis_result")
public class AnalysisResult {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "message")
    private String message;
    @Column(name = "status")
    private int status;
    @Column(name = "destination")
    private String destination;
    @Column(name = "diagnose")
    private String diagnose;
    @Column(name = "date_of_consultation")
    @NotEmpty(message = "Not should be empty")
    private String dateOfConsultation;
    @OneToOne(mappedBy = "result")
    private LastResult lastResult;
}
