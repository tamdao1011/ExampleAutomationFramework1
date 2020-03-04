package common;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ExcelReport implements ITestListener {
    public int numberPassedTests = 0;
    public int numberFailedTests = 0;
    public int numberSkipTest = 0;
    public long durations = 0;
    public HashMap<String, HashMap<String, String>> suiteResult = new HashMap<>();

    public void onStart(ITestContext context) {
        System.out.println("On start of test " + context.getName());
    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test method " + getTestMethodName(result) + " starts");
    }

    public void onTestSuccess(ITestResult result) {
        onTestResult(result, "Passed");
    }

    public void onTestFailure(ITestResult result) {
        onTestResult(result, "Failed");
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test method " + getTestMethodName(result) + " skipped");
        numberSkipTest++;
    }

    public void onFinish(ITestContext context) {
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String fileName = "Report_" + context.getName() + "_" + time + ".xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create Result sheet
        XSSFSheet sheet = workbook.createSheet("Result");

        //Create formats for cell style
        XSSFCellStyle defaultStyle = setStyle(sheet, "Default");
        XSSFCellStyle failedStyle = setStyle(sheet, "Failed");
        XSSFCellStyle passedStyle = setStyle(sheet, "Passed");
        XSSFCellStyle normalStyle = setStyle(sheet, "Normal");

        //Input value for the header and the summary table
        Row titleRow = sheet.createRow(0);
        Row summaryRow = sheet.createRow(3);
        Row summaryRowDetails = sheet.createRow(4);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

        createAndSetCellStyleValue(titleRow.createCell(0), defaultStyle, "Summary Report", null);
        createAndSetCellStyleValue(summaryRow.createCell(0), defaultStyle, "PASSED", null);
        createAndSetCellStyleValue(summaryRowDetails.createCell(0), passedStyle, null, numberPassedTests);
        createAndSetCellStyleValue(summaryRow.createCell(1), defaultStyle, "FAILED", null);
        createAndSetCellStyleValue(summaryRowDetails.createCell(1), failedStyle, null, numberFailedTests);
        createAndSetCellStyleValue(summaryRow.createCell(2), defaultStyle, "N/A", null);
        createAndSetCellStyleValue(summaryRowDetails.createCell(2), normalStyle, null, numberSkipTest);
        createAndSetCellStyleValue(summaryRow.createCell(3), defaultStyle, "PASS RATE", null);
        createAndSetCellStyleValue(summaryRow.createCell(4), defaultStyle, "DURATIONS(second)", null);
        createAndSetCellStyleValue(summaryRowDetails.createCell(4), normalStyle, null, (int) durations);

        XSSFCellStyle passRate = setStyle(sheet, "Passed");
        passRate.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        Cell passRateValue = summaryRowDetails.createCell(3);
        passRateValue.setCellStyle(passRate);
        passRateValue.setCellFormula("A5/SUM(A5:B5)");

        //Input detail results
        Row detailRow = sheet.createRow(7);
        createAndSetCellStyleValue(detailRow.createCell(0), defaultStyle, "No", null);
        createAndSetCellStyleValue(detailRow.createCell(1), defaultStyle, "Feature", null);
        createAndSetCellStyleValue(detailRow.createCell(2), defaultStyle, "TC Name", null);
        createAndSetCellStyleValue(detailRow.createCell(3), defaultStyle, "Result", null);
        createAndSetCellStyleValue(detailRow.createCell(4), defaultStyle, "Error Logs", null);
        createAndSetCellStyleValue(detailRow.createCell(5), defaultStyle, "Durations", null);

        int noNum = 1;
        int firstRow = 8;
        for (String key : suiteResult.keySet()) {
            Row tcDetails = sheet.createRow(firstRow);
            createAndSetCellStyleValue(tcDetails.createCell(0), normalStyle, null, noNum);
            createAndSetCellStyleValue(tcDetails.createCell(1), normalStyle, context.getName(), null);
            createAndSetCellStyleValue(tcDetails.createCell(2), normalStyle, suiteResult.get(key).get("TC_Name"), null);

            Cell cellResult = tcDetails.createCell(3);
            cellResult.setCellStyle(normalStyle);
            Cell cellErrorLog = tcDetails.createCell(4);
            cellErrorLog.setCellStyle(normalStyle);
            if (suiteResult.get(key).get("Result").equals("Failed")) {
                createAndSetCellStyleValue(cellResult, failedStyle, suiteResult.get(key).get("Result"), null);
                createAndSetCellStyleValue(cellErrorLog, failedStyle, suiteResult.get(key).get("Error_Log"), null);
            } else {
                createAndSetCellStyleValue(cellResult, passedStyle, suiteResult.get(key).get("Result"), null);
            }
            createAndSetCellStyleValue(tcDetails.createCell(5), normalStyle, suiteResult.get(key).get("Durations"), null);
            noNum++;
            firstRow++;
        }

        //Create excel file
        try (FileOutputStream outputStream = new FileOutputStream(System.getProperty("user.dir") + "/src/test/java/reports/excel/" + fileName)) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //private functions
    private void onTestResult(ITestResult result, String status) {
        long tcDuration = result.getEndMillis() - result.getStartMillis();
        durations = durations + tcDuration / 60;
        HashMap<String, String> testResult = new HashMap<>();
        testResult.put("TC_Name", getTestMethodName(result));
        if (status.equalsIgnoreCase("Passed")) {
            numberPassedTests++;
            testResult.put("Result", "Passed");
        } else {
            numberFailedTests++;
            testResult.put("Result", "Failed");
            testResult.put("Error_Log", result.getThrowable().toString());
        }
        testResult.put("Durations", String.valueOf(tcDuration / 60));
        suiteResult.put(getTestMethodName(result), testResult);
    }

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    private static void setBorderThin(XSSFCellStyle cellStyle) {
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
    }

    private static XSSFCellStyle setStyle(XSSFSheet sheet, String status) {
        XSSFCellStyle style = sheet.getWorkbook().createCellStyle();
        XSSFFont font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 13);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        setBorderThin(style);
        switch (status) {
            case "Passed":
                font.setColor(IndexedColors.GREEN.getIndex());
                return style;
            case "Failed":
                font.setColor(IndexedColors.RED.getIndex());
                return style;
            case "Normal":
                font.setColor(IndexedColors.BLACK.getIndex());
                font.setBold(false);
                style.setAlignment(HorizontalAlignment.LEFT);
                return style;
            default:
                font.setColor(IndexedColors.WHITE.getIndex());
                style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                return style;
        }
    }

    private void createAndSetCellStyleValue(@NotNull Cell cell, XSSFCellStyle style, @Nullable String stringValue, @Nullable Integer numberValue) {
        cell.setCellStyle(style);
        if (stringValue != null) cell.setCellValue(stringValue);
        else if (numberValue != null) cell.setCellValue(numberValue);
    }

}