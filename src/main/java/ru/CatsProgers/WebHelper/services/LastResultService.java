package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.AnalysisResult;
import ru.CatsProgers.WebHelper.models.LastResult;
import ru.CatsProgers.WebHelper.repositories.LastResultRepository;

@Service
public class LastResultService {
    private final LastResultRepository lastResultRepository;
    @Autowired
    public LastResultService(LastResultRepository lastResultRepository) {
        this.lastResultRepository = lastResultRepository;
    }
    @Transactional
    public void saveLastResult(LastResult lastResult){
        lastResultRepository.save(lastResult);
    }
    @Transactional
    public void saveLastResultAndHisFields(AnalysisResult analysisResult){
        LastResult lastResult = new LastResult();
        lastResult.setResult(analysisResult);
        lastResult.setStatus(analysisResult.getStatus());
        lastResult.setDateOfConsultation(analysisResult.getDateOfConsultation());
        saveLastResult(lastResult);
    }
}
