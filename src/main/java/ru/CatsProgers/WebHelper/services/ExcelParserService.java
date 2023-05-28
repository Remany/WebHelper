package ru.CatsProgers.WebHelper.services;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.CatsProgers.WebHelper.models.Patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelParserService {
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
}

