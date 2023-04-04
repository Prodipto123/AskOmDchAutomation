package com.selenium.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import com.selenium.reporting.Reporter;

public class ExcelHelper {

	String dir = System.getProperty("user.dir");
	String fileName;
	HashMap<String, String> innerTestDataIterationMap;
	List<Map<String, String>> outerTestDataMap = new ArrayList<Map<String, String>>();

	FileInputStream fis;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public ExcelHelper(String fileName) {

		try {
			fis = new FileInputStream(dir + fileName);
			try {
				workbook = new XSSFWorkbook(fis);
				sheet = workbook.getSheetAt(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("IO Exception " + e.getMessage());
				Reporter.fail("IO Exception " + e.getMessage());
				Assert.fail();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Excel File Not Found " + e.getMessage());
			Reporter.fail("Excel File Not Found " + e.getMessage());
			Assert.fail();
		}

	}

	public List<Map<String, String>> getExclData() {

		List<String> headerList = new ArrayList<String>();
		int columnCount;
		int rowCount;
		XSSFCell cell;
		XSSFRow row;
		int counter;
		int colCounter;
		int rowCnt;
		boolean flagYValueFound = false;

		Object cellVal;
		String actualStringCellValue = null;

		try {
			rowCount = sheet.getLastRowNum();
			columnCount = sheet.getRow(0).getLastCellNum();

			for (counter = 0; counter < columnCount; counter++) {

				headerList.add(sheet.getRow(0).getCell(counter).getStringCellValue());		
			}
			
			for(rowCnt=1;rowCnt<=rowCount;rowCnt++) {
				
				flagYValueFound = true;
				innerTestDataIterationMap = new HashMap<String, String>();
				row = sheet.getRow(rowCnt);
				
				for(colCounter=1;colCounter<columnCount;colCounter++) {
					
					if(row.getCell(0).getStringCellValue().equalsIgnoreCase("Y")) {
						
						cell = row.getCell(colCounter, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
						
						if(cell !=null) {
							
							if(cell.getCellType()== CellType.BOOLEAN) {
								
								cellVal = cell.getBooleanCellValue();
								actualStringCellValue = String.valueOf(cellVal);
								
							}else if(cell.getCellType()== CellType.NUMERIC) {
								
								cellVal = cell.getNumericCellValue();
								actualStringCellValue = String.valueOf(cellVal);
								
							}else if(cell.getCellType()== CellType.STRING) {
								
								actualStringCellValue = cell.getStringCellValue();
								
							}
							
						}else
						{
							actualStringCellValue = "";
						}
						
						innerTestDataIterationMap.put(headerList.get(colCounter), actualStringCellValue);
						flagYValueFound = true;
						
					}else {
						
						innerTestDataIterationMap = null;
						flagYValueFound = false;
						break;
					}
					
					
					
				}
				
				if(flagYValueFound) {
					
					outerTestDataMap.add(innerTestDataIterationMap);
					innerTestDataIterationMap = null;
				}
				
				
			}

		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println("File reading error " + e.getMessage());
			Reporter.fail("File reading error " + e.getMessage());
			Assert.fail();
		}
		
		return outerTestDataMap;

	}
}
