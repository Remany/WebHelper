package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.AnalysisResult;

@Service
@Transactional(readOnly = true)
public class LastResultsService {
    private final LastResultsRepository lastResultsRepository;
    @Autowired
    public LastResultsService(LastResultsRepository lastResultsRepository) {
        this.lastResultsRepository = lastResultsRepository;
    }
    @Transactional
    public void addResult(AnalysisResult analysisResult){
        lastResultsRepository.save(analysisResult);
    }
}
