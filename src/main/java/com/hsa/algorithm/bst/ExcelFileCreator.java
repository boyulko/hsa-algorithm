package com.hsa.algorithm.bst;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileCreator {

  public static Sheet createSheet(Workbook workbook, String sheetName) {

    Sheet sheet = workbook.createSheet(sheetName);
    sheet.setColumnWidth(0, 6000);
    sheet.setColumnWidth(1, 4000);

    Row header = sheet.createRow(0);

    CellStyle headerStyle = workbook.createCellStyle();
    headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
    headerStyle.setFillPattern((short)1);

    XSSFFont font = ((XSSFWorkbook) workbook).createFont();
    font.setFontName("Arial");
    font.setFontHeightInPoints((short) 16);
    font.setBold(true);
    headerStyle.setFont(font);

    Cell headerCell = header.createCell(0);
    headerCell.setCellValue("Element number");
    headerCell.setCellStyle(headerStyle);

    headerCell = header.createCell(1);
    headerCell.setCellValue("Duration of an Operation");
    headerCell.setCellStyle(headerStyle);
    return sheet;
  }

  public static void insertElementInfo(Sheet sheet, long elementNumber, long operationDuration) {

    Row row = sheet.createRow((int)elementNumber);
    Cell cell = row.createCell(0);
    cell.setCellValue(elementNumber);


    cell = row.createCell(1);
    cell.setCellValue(operationDuration);
  }

}
