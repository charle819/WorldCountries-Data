package com.foobar.WorldData.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.foobar.WorldData.model.City;



@Component
public class WorldDataExcelGenerator {

	private static final Logger LOGGER = Logger.getLogger(WorldDataExcelGenerator.class.getName());
	private static final String CITY_Excel_TEMPLATE = "city-excel-template.xlsx";
	
	
	public Workbook generateCityExcelSheet(List<City> cityList) {
		Workbook workbook = null;

		try {
			ClassLoader loader = getClass().getClassLoader();
			
			LOGGER.info("The classloder  = "+loader.toString());
			
			File file = new File(loader.getResource(CITY_Excel_TEMPLATE).getFile());
			FileInputStream fileInputStream = new FileInputStream(file);
			workbook = new XSSFWorkbook(fileInputStream);
			Sheet sheet = workbook.getSheetAt(0);

			int rowCount = 1;
			for (City c : cityList) {
				Row row = sheet.createRow(++rowCount);
				writeCityData(c, row);
			}
			
			autoSizeColumns(workbook);
			fileInputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return workbook;
	}

	private  void writeCityData(City c, Row row) {
		Cell cell = row.createCell(1);
		cell.setCellValue(c.getCity_name());

		cell = row.createCell(2);
		cell.setCellValue(c.getCity_countryCode());

		cell = row.createCell(3);
		cell.setCellValue(c.getCity_district());

		cell = row.createCell(4);
		cell.setCellValue(c.getCity_population());

	}

	// FOR AUTOEXAPANDING EVERY CELL AS PER THEIR SIZE
	private  void autoSizeColumns(Workbook workbook) {
		int numberOfSheets = workbook.getNumberOfSheets();
		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			if (sheet.getPhysicalNumberOfRows() > 0) {
				Row row = sheet.getRow(1); // rows are starting from 1st index
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int columnIndex = cell.getColumnIndex();
					sheet.autoSizeColumn(columnIndex);
				}
			}
		}
	}

}
