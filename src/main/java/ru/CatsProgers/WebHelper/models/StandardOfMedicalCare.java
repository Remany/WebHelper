package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Profiles")
public class StandardOfMedicalCare {
    @Id
    @Column(name = "diagnose_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int diagnoseId;
    @Column(name = "diagnose")
    private String diagnose;
    @Column(name = "doctor_profile")
    private String profile;
    @Column(name = "medical_service_code")
    private String medicalServiceCode;
    @Column(name = "name_of_medical_service")
    private String destination;
}
