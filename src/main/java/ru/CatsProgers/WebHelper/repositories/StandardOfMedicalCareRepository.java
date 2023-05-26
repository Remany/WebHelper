package ru.CatsProgers.WebHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.CatsProgers.WebHelper.models.StandardOfMedicalCare;

import java.util.Optional;

@Repository
public interface StandardOfMedicalCareRepository extends JpaRepository<StandardOfMedicalCare, Integer> {
    Optional<StandardOfMedicalCare> findStandardOfMedicalCareByDiagnose(String diagnose);
    Optional<StandardOfMedicalCare> findStandardOfMedicalCareByNameOfMedicalService(String nameOfMedicalService);
}
