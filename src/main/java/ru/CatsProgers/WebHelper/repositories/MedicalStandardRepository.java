package ru.CatsProgers.WebHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.CatsProgers.WebHelper.models.MedicalStandard;

import java.util.Optional;

@Repository
public interface MedicalStandardRepository extends JpaRepository<MedicalStandard, Integer> {
    Optional<MedicalStandard> findMedicalStandardByDiagnose(String diagnose);
    Optional<MedicalStandard> findMedicalStandardByDestination(String destination);
}

