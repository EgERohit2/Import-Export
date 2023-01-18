package com.example.demo.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.entities.Workers;

public class ExcelGenerator {

	private List<Workers> workersList;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public ExcelGenerator(List<Workers> workerList) {
		super();
		this.workersList = workerList;
		this.workbook = new XSSFWorkbook();
	}

	public ExcelGenerator() {
		super();
	}
	
	private void createCell(Row row, int cellCount, Object valueOfCell, CellStyle style) {
		sheet.autoSizeColumn(cellCount);
		Cell cell = row.createCell(cellCount);
		if (valueOfCell instanceof Integer) {
			cell.setCellValue((Integer) valueOfCell);
		} else if (valueOfCell instanceof Long) {
			cell.setCellValue((Long) valueOfCell);
		} else if (valueOfCell instanceof Boolean) {
			cell.setCellValue((Boolean) valueOfCell);
		} else {
			cell.setCellValue((String) valueOfCell);
		}
		cell.setCellStyle(style);

	}

	private void writeHeader() {
		sheet = workbook.createSheet("Workers");
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16.00);
		style.setFont(font);
		createCell(row, 0, "ID", style);
		createCell(row, 1, "Worker name", style);
		createCell(row, 2, "Worker email", style);
		createCell(row, 3, "Worker mobileno", style);

	}

	

	private void write() {
		int rowCount = 2;
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		for (Workers record : workersList) {

			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, record.getId(), style);
			createCell(row, columnCount++, record.getName(), style);
			createCell(row, columnCount++, record.getEmail(), style);
			createCell(row, columnCount++, record.getMobileno(), style);
		}
	}

	public void generateExcelFile(HttpServletResponse response) throws IOException {
		writeHeader();
		write();
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

}
