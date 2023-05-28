package ru.CatsProgers.WebHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.Patient;
import ru.CatsProgers.WebHelper.repositories.PatientRepository;

@Service
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Transactional
    public void savePatient(Patient patient){
        patientRepository.save(patient);
    }
}
