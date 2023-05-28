package ru.CatsProgers.WebHelper.services;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.AnalysisResult;
import ru.CatsProgers.WebHelper.models.Consultation;
import ru.CatsProgers.WebHelper.models.MedicalStandard;
import ru.CatsProgers.WebHelper.repositories.AnalysisResultRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AnalysisResultService {
    private final EntityManager entityManager;
    private final StandardService standardService;
    private final LastResultService lastResultService;
    private final AnalysisResultRepository analysisResultRepository;

    @Autowired
    public AnalysisResultService(StandardService standardService, AnalysisResultRepository analysisResultRepository, EntityManager entityManager, LastResultService lastResultService) {
        this.standardService = standardService;
        this.analysisResultRepository = analysisResultRepository;
        this.entityManager = entityManager;
        this.lastResultService = lastResultService;
    }
    @Transactional
    public void saveAnalysis(AnalysisResult analysisResult){
        analysisResultRepository.save(analysisResult);
    }
    @Transactional
    public void removeLastAnalysis(){
        Session session = entityManager.unwrap(Session.class);
        List<AnalysisResult> lastResult = session.createQuery("FROM AnalysisResult").getResultList();
        if (!lastResult.isEmpty()){
            session.delete(lastResult.get(0));
        }
    }
    @Transactional
    public void saveNewAnalysisEntity(Consultation consultation){
        removeLastAnalysis();
        AnalysisResult result = new AnalysisResult();
        result.setDestination(consultation.getDestination());
        result.setDiagnose(consultation.getDiagnose());
        result.setDateOfConsultation(consultation.getDateOfConsultation());
        saveAnalysis(result);
    }
    @Transactional
    public void setMessageAndStatus(Optional<MedicalStandard> standard, AnalysisResult result){
        if (standard.isPresent()){
            Optional<MedicalStandard> destination = standardService.getStandardByDestination(result.getDestination());
            if (destination.isPresent()) {
                result.setMessage("Назначения соответствуют стандарту оказания медицинской помощи");
                result.setStatus(1);
            } else {
                result.setMessage("Сделаны дополнительные назначения, не входящие в стандарт оказания медицинской помощи");
                result.setStatus(2);
            }
        } else {
            result.setMessage("Для данного диагноза не разработан стандарт оказания медицинской помощи");
            result.setStatus(3);
        }
        lastResultService.saveLastResultAndHisFields(result);
        saveAnalysis(result);
    }
    @Transactional
    public AnalysisResult getResult(){
        Session session = entityManager.unwrap(Session.class);
        List<AnalysisResult> loadResult = session.createQuery("FROM AnalysisResult").getResultList();
        AnalysisResult result = loadResult.get(0);
        Optional<MedicalStandard> standard = standardService.getStandardByDiagnose(result.getDiagnose());
        setMessageAndStatus(standard, result);
        return result;
    }
}
