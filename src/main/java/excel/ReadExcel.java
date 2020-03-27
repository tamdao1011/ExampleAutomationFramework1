package excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static excel.ExcelCommon.accessExcelFile;
import static excel.ExcelCommon.getCellValue;

public class ReadExcel {

    public static String[] getDataFromRegistrationInvalidTable(String valueType) {
        int zero = 0;
        String[] result = new String[4];
        String cellValue;
        XSSFWorkbook workbook = accessExcelFile();
        if (workbook != null) {
            XSSFSheet sheet = workbook.getSheetAt(zero);
            Row row = sheet.getRow(zero);
            cellValue = getCellValue(row, zero);
            int i = zero;
            while (!cellValue.equalsIgnoreCase(valueType)) {
                i++;
                row = sheet.getRow(i);
                cellValue = getCellValue(row, zero);
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

}
