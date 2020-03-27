package excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelCommon {

    public static XSSFWorkbook accessExcelFile() {
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

    public static String getCellValue(Row row, int cellNumber) {
        String cellValue;
        try {
            cellValue = row.getCell(cellNumber).getStringCellValue();
        } catch (IllegalStateException e) {
            cellValue = String.valueOf((int) row.getCell(cellNumber).getNumericCellValue());
        }
        return cellValue;
    }

}
