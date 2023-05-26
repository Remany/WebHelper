package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Result")
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
}
