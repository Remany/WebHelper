package ru.CatsProgers.WebHelper.services;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.CatsProgers.WebHelper.models.Patient;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExcelParserService {
    // Нужно засунуть данные в лист объектов
    public static List<Patient> readExcel(String path, List<Patient> patients) {
        Workbook wb;
        try {
            wb = new XSSFWorkbook(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = wb.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.println(getCellText(cell));
            }
        }
        return patients;
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
}
