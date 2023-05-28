package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
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
    private String dateOfConsultation;
    @Column(name = "destination")
    private String destination;
    @Column(name = "diagnose")
    private String diagnose;
    @Column(name = "code_mkb_10")
    private String codeMKB;
    @Column(name = "doctor_profile")
    private String profile;
    // TODO реализовать связи между таблицамии
}
