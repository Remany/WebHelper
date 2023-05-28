package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.Consultation;
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
    public void saveConsultation(Consultation consultation){
        consultationRepository.save(consultation);
    }
    public Consultation getConsultationById(int id){
       Optional<Consultation> foundConsultation = consultationRepository.findConsultationById(id);
       return foundConsultation.orElseThrow(ConsultationNotFoundException::new);
    }
    public Consultation getConsultationByDiagnose(String diagnose){
        Optional<Consultation> foundConsultation = consultationRepository.findConsultationByDiagnose(diagnose);
        return foundConsultation.orElseThrow(ConsultationNotFoundException::new);
    }
}
