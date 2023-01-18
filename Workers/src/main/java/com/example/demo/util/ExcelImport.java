package com.example.demo.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.entities.Workers;

public class ExcelImport {

	public List<Workers> ExcelImport() throws FileNotFoundException {

		List<Workers> wor = new ArrayList<>();
		int id = 0;
		String name = "";
		String email = "";
		String mobileno = "";

		String filepath = "C:\\Users\\dell\\Downloads\\Employee (1).xlsx";
		long start = System.currentTimeMillis();
		try {
		FileInputStream fileinput;
		
			fileinput = new FileInputStream(filepath);

			Workbook workbook = new XSSFWorkbook(fileinput);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row nextRow = rowIterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {

					case 0:
						id = (int) nextCell.getNumericCellValue();
						System.out.println(id);
						break;

					case 1:
						name = nextCell.getStringCellValue();
						System.out.println(name);
						break;

					case 2:
						email = nextCell.getStringCellValue();
						System.out.println(email);
						break;

					case 3:
						mobileno = nextCell.getStringCellValue();
						System.out.println(mobileno);
						break;
					}
					wor.add(new Workers(id, name, email, mobileno));
				}

			}
			workbook.close();
			long end = System.currentTimeMillis();
			System.out.println((end - start));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return wor;

	}

}
