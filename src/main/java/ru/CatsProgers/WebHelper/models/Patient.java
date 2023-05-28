package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "gender")
    @NotEmpty(message = "Not should be empty")
    private String gender;
    @Column(name = "date_of_birth")
    @NotEmpty(message = "Not should be empty")
    private String dateOfBirth;
    @Column(name = "name")
    @NotEmpty(message = "Not should be empty")
    private String name;
    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;
}