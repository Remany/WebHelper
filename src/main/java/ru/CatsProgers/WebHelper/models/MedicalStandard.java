package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
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
    private String diagnose;
    @Column(name = "doctor_profile")
    private String profile;
    @Column(name = "destination_code")
    private String medicalServiceCode;
    @Column(name = "destination")
    private String destination;
}
