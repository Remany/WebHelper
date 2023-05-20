package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.Entity;
import lombok.Data;
import ru.CatsProgers.WebHelper.utils.Doctor;
import ru.CatsProgers.WebHelper.utils.Male;

import java.util.Date;
import java.util.List;

@Data
public class Patient {
    private Male male;
    private Date dateOfBirth;
    private int id;
    private String codeMKB;
    private Date dateIfService;
    private Doctor doctor;
    private List<String> checkups;
}