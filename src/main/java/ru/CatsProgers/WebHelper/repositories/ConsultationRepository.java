package ru.CatsProgers.WebHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.CatsProgers.WebHelper.models.DoctorConsultation;

import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<DoctorConsultation, Integer> {
    Optional<DoctorConsultation> findDoctorConsultationByDiagnose(String diagnose);
    Optional<DoctorConsultation> findDoctorConsultationByDestination(String destination);
    Optional<DoctorConsultation> findDoctorConsultationById(int id);
}
