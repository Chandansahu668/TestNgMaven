package com.test.Utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataProvider {
	XSSFWorkbook workbook;
	static XSSFSheet sheet;
	ReadConfig readConfig = new ReadConfig();
	public DataProvider() {
		try {
			workbook= new XSSFWorkbook("./Resource/data.xlsx");
			sheet = workbook.getSheet(readConfig.prop.getProperty("Project"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public String getCellData(String TCName,String columnName) {
		return sheet.getRow(getTestCaseRow(TCName)).getCell(getTestCaseColumn(columnName)).getStringCellValue();
	}

	public int getTestCaseRow(String TestCaseName) {
		int rowNum = 0;
		for (int i =1; i<=sheet.getLastRowNum();i++) {
			if(sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(TestCaseName)) {
				rowNum= i;
				break;
			}
		}
		return rowNum;
	}

	public int getTestCaseColumn(String columnName) {
		int colNum = 0;
		for (int i =1; i<=sheet.getRow(0).getLastCellNum();i++) {
			if(sheet.getRow(0).getCell(i).getStringCellValue().equals(columnName)) {
				colNum= i;
				break;
			}
		}
		return colNum;
	}

}
