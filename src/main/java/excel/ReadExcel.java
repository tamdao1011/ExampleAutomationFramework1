package excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {

    public static String[] getDataFromExcel(String valueType) {
        int i = 0;
        String[] result = new String[4];
        String cellValue;
        XSSFWorkbook workbook = accessExcelFile();
        if (workbook != null) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            cellValue = getCellValue(row, 0);
            while (!cellValue.equalsIgnoreCase(valueType)) {
                i++;
                row = sheet.getRow(i);
                cellValue = getCellValue(row, 0);
            }
            result[0] = getCellValue(row, 1);
            for (int j = 2; j <= 4; j++) {
                String temp = getCellValue(row, j);
                if (temp.equalsIgnoreCase("0"))
                    result[j - 1] = "";
                result[j - 1] = temp;
            }
        }
        return result;
    }

    private static String getCellValue(Row row, int cellNumber) {
        String cellValue;
        try {
            cellValue = row.getCell(cellNumber).getStringCellValue();
        } catch (IllegalStateException e) {
            cellValue = String.valueOf((int) row.getCell(cellNumber).getNumericCellValue());
        }
        return cellValue;
    }

    private static XSSFWorkbook accessExcelFile() {
        FileInputStream fis;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream("src/main/java/excel/registration_invalid.xlsx");
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
