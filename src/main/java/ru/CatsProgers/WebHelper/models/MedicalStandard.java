package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "medical_standard")
public class MedicalStandard {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diagnoseId;
    @Column(name = "diagnose")
    @NotEmpty(message = "Not should be empty")
    private String diagnose;
    @Column(name = "doctor_profile")
    @NotEmpty(message = "Not should be empty")
    private String profile;
    @Column(name = "destination_code")
    @NotEmpty(message = "Not should be empty")
    private String medicalServiceCode;
    @Column(name = "destination")
    private String destination;
    @OneToOne(mappedBy = "standard")
    private Consultation consultation;
}
