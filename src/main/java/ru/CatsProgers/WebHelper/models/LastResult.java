package ru.CatsProgers.WebHelper.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "last_results")
public class LastResult {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "status")
    private int status;
    @Column(name = "date_of_consultation")
    private String dateOfConsultation;
    @OneToOne
    @JoinColumn(name = "result_id", referencedColumnName = "id")
    private AnalysisResult result;
}
