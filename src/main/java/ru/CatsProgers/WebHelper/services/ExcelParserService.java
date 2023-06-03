package ru.CatsProgers.WebHelper.services;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.CatsProgers.WebHelper.models.Consultation;
import ru.CatsProgers.WebHelper.models.Patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelParserService {
    private final AnalysisResultService analysisResultService;
    private final ConsultationService consultationService;
    private final PatientService patientService;
    @Autowired
    public ExcelParserService(AnalysisResultService analysisResultService, ConsultationService consultationService, PatientService patientService) {
        this.analysisResultService = analysisResultService;
        this.consultationService = consultationService;
        this.patientService = patientService;
    }

    public static List<String> readExcel(String path) {
        Workbook wb;
        try {
            wb = new XSSFWorkbook(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = wb.getSheetAt(0);
        List<String> cellTexts = new ArrayList<>();

        for (Row row : sheet) {
            for (Cell cell : row) {
                cellTexts.add(getCellText(cell));
            }
        }
        return cellTexts;
    }

    private static String getCellText(Cell cell) {
        String result;

        switch (cell.getCellType()) {
            case STRING:
                result = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    result = date.toString();
                } else {
                    result = Double.toString(cell.getNumericCellValue());
                }
                break;
            default:
                result = "";
        }
        return result;
    }
    @Transactional
    public void setValuesInFields(List<String> cellTexts){
        Consultation consultation = new Consultation();
        Patient patient = new Patient();
        patient.setName(cellTexts.get(0));
        patient.setGender(cellTexts.get(1));
        patient.setDateOfBirth(cellTexts.get(2));
        patientService.savePatient(patient);
        consultation.setPatient(patient);
        consultation.setCodeMKB(cellTexts.get(3));
        consultation.setDiagnose(cellTexts.get(4));
        consultation.setDateOfConsultation(cellTexts.get(5));
        consultation.setProfile(cellTexts.get(6));
        consultation.setDestination(cellTexts.get(7));
        consultationService.saveConsultation(consultation);
        analysisResultService.saveNewAnalysisEntity(consultation);
    }
}

