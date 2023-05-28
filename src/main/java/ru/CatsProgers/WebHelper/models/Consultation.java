package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "consultation")
public class Consultation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_of_consultation")
    @NotEmpty(message = "Not should be empty")
    private String dateOfConsultation;
    @Column(name = "destination")
    @NotEmpty(message = "Not should be empty")
    private String destination;
    @Column(name = "diagnose")
    @NotEmpty(message = "Not should be empty")
    private String diagnose;
    @Column(name = "code_mkb_10")
    private String codeMKB;
    @Column(name = "doctor_profile")
    @NotEmpty(message = "Not should be empty")
    private String profile;
    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    @OneToOne
    @JoinColumn(name = "standard_id", referencedColumnName = "id")
    private MedicalStandard standard;
}
