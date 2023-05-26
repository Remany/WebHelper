package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "doctor_consultation")
public class DoctorConsultation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "date_of_service")
    private Date dateOfBirth;
    @Column(name = "destination")
    private String destination;
    @Column(name = "diagnose")
    private String diagnose;
    @Column(name = "code_mkb_10")
    private String codeMKB;
    @Column(name = "doctor_profile")
    private String profile;
}
