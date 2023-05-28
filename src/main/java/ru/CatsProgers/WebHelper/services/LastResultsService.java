package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.AnalysisResult;
import ru.CatsProgers.WebHelper.models.LastResult;
import ru.CatsProgers.WebHelper.repositories.LastResultRepository;

@Service
@Transactional(readOnly = true)
public class LastResultsService {
    private final LastResultRepository lastResultRepository;
    @Autowired
    public LastResultsService(LastResultRepository lastResultRepository) {
        this.lastResultRepository = lastResultRepository;
    }
    @Transactional
    public void addResult(LastResult lastResult){
        lastResultRepository.save(lastResult);
    }
}
