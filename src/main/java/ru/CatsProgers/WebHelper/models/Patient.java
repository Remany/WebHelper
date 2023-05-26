package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import lombok.Data;
import ru.CatsProgers.WebHelper.utils.Gender;

import java.util.Date;

@Data
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
}