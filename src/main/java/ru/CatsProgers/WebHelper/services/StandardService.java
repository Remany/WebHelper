package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.StandardOfMedicalCare;
import ru.CatsProgers.WebHelper.repositories.StandardOfMedicalCareRepository;
import ru.CatsProgers.WebHelper.utils.StandardNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StandardService {
    private final StandardOfMedicalCareRepository standardOfMedicalCareRepository;
    private final ConsultationService consultationService;
    @Autowired
    public StandardService(StandardOfMedicalCareRepository medicalProfileRepository, ConsultationService consultationService) {
        this.standardOfMedicalCareRepository = medicalProfileRepository;
        this.consultationService = consultationService;
    }

    public Optional<StandardOfMedicalCare> getStandardByDiagnose(String diagnose){
        return standardOfMedicalCareRepository.findStandardOfMedicalCareByDiagnose(diagnose);
    }

    public StandardOfMedicalCare getStandardByConsultationId(int id){
        Optional<StandardOfMedicalCare> foundStandard = standardOfMedicalCareRepository
                .findStandardOfMedicalCareByDiagnose(consultationService.getConsultationById(id).getDiagnose());
        return foundStandard.orElseThrow(StandardNotFoundException::new);
    }
}
