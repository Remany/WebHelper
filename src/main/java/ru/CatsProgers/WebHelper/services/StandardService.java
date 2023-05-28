package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.MedicalStandard;
import ru.CatsProgers.WebHelper.repositories.MedicalStandardRepository;
import ru.CatsProgers.WebHelper.utils.StandardNotFoundException;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StandardService {
    private final MedicalStandardRepository medicalStandardRepository;
    private final ConsultationService consultationService;
    @Autowired
    public StandardService(MedicalStandardRepository medicalProfileRepository, ConsultationService consultationService) {
        this.medicalStandardRepository = medicalProfileRepository;
        this.consultationService = consultationService;
    }
    public Optional<MedicalStandard> getStandardByDiagnose(String diagnose){
        return medicalStandardRepository.findMedicalStandardByDiagnose(diagnose);
    }
    public MedicalStandard getStandardByConsultationId(int id){
        Optional<MedicalStandard> foundStandard = medicalStandardRepository
                .findMedicalStandardByDiagnose(consultationService.getConsultationById(id).getDiagnose());
        return foundStandard.orElseThrow(StandardNotFoundException::new);
    }
    public Optional<MedicalStandard> getStandardByDestination(String destination){
        Optional<MedicalStandard> foundStandard =
                medicalStandardRepository.findMedicalStandardByDestination(destination);
        return foundStandard;
    }
}
