package ru.CatsProgers.WebHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.CatsProgers.WebHelper.models.Consultation;

import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    Optional<Consultation> findConsultationByDiagnose(String diagnose);
    Optional<Consultation> findConsultationByDestination(String destination);
    Optional<Consultation> findConsultationById(int id);
}
