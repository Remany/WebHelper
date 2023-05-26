package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.DoctorConsultation;
import ru.CatsProgers.WebHelper.repositories.ConsultationRepository;
import ru.CatsProgers.WebHelper.utils.ConsultationNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    @Autowired
    public ConsultationService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }
    @Transactional
    public void saveConsultation(DoctorConsultation consultation){
        consultationRepository.save(consultation);
    }
    public DoctorConsultation getConsultationById(int id){
       Optional<DoctorConsultation> foundConsultation = consultationRepository.findDoctorConsultationById(id);
       return foundConsultation.orElseThrow(ConsultationNotFoundException::new);
    }
    public DoctorConsultation getConsultationByDiagnose(String diagnose){
        Optional<DoctorConsultation> foundConsultation = consultationRepository.findDoctorConsultationByDiagnose(diagnose);
        return foundConsultation.orElseThrow(ConsultationNotFoundException::new);
    }
}
