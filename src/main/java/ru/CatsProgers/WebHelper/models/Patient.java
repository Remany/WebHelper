package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "gender")
    private String gender;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    // TODO реализовать логику сета id консультаций, она должна быть по идеи на стороне конусльтаций
}