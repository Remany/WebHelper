package ru.CatsProgers.WebHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.CatsProgers.WebHelper.models.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
