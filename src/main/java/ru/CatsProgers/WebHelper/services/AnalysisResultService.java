package ru.CatsProgers.WebHelper.services;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.AnalysisResult;
import ru.CatsProgers.WebHelper.models.DoctorConsultation;
import ru.CatsProgers.WebHelper.models.StandardOfMedicalCare;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AnalysisResultService {
    private final ConsultationService consultationService;
    private final StandardService standardService;
    private final EntityManager entityManager;
    @Autowired
    public AnalysisResultService(ConsultationService consultationService, StandardService standardService, EntityManager entityManager) {
        this.consultationService = consultationService;
        this.standardService = standardService;
        this.entityManager = entityManager;
    }

    @Transactional
    public void removeLastAnalysis(){
        Session session = entityManager.unwrap(Session.class);
        List<AnalysisResult> lastResult = session.createQuery("FROM AnalysisResult").getResultList();
        session.delete(lastResult.get(0));
    }

    @Transactional
    public void saveNewAnalysisEntity(DoctorConsultation consultation){
        removeLastAnalysis();
        AnalysisResult result = new AnalysisResult();
        result.setDestination(consultation.getDestination());
        result.setDiagnose(consultation.getDiagnose());
    }
    public AnalysisResult getResult(){
        Session session = entityManager.unwrap(Session.class);
        List<AnalysisResult> loadResult = session.createQuery("FROM AnalysisResult").getResultList();
        AnalysisResult result = loadResult.get(0);
        Optional<StandardOfMedicalCare> standard = standardService.getStandardByDiagnose(result.getDiagnose());
        if (standard.isPresent()){
            if (standard.get().getNameOfMedicalService().equals(result.getDestination())) {
                result.setMessage("Назначения соответствуют стандарту оказания медицинской помощи");
            } else {
                result.setMessage("Сделаны дополнительные назначения, не входящие в стандарт оказания медицинской помощи");
            }
        } else {
            result.setMessage("Для данного диагноза не разработан стандарт оказания медицинской помощи");
        }
        return result;
    }
}
