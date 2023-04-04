package com.selenium.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.DataProvider;

public class CSVHelper {

	String dir = System.getProperty("user.dir");
	String fileName;
	BufferedReader br;
	HashMap<String, HashMap<String, String>> outerTestDataMap = new HashMap<String, HashMap<String, String>>();
	HashMap<String, String> innerTestDataIterationMap;

	public CSVHelper(String fileName) {

		try {
			br = new BufferedReader(new FileReader(dir + fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
	
	public HashMap<String, HashMap<String, String>> getCSVFileData() {

		String line;
		String colNames;
		List<String> headerList = new ArrayList<String>();
		List<String> strLoginDeails;
		String sRow = "Row";
		int currItr = 1;

		try {
			colNames = br.readLine();

			String[] cols = colNames.split(",");

			for (int i = 0; i < cols.length; i++) {
				headerList.add(cols[i]);

			}

			// System.out.println(headerList);
			while ((line = br.readLine()) != null) {
				
			if(line.startsWith("Y"))	
			  {

				//if (!(line.startsWith("OU"))) {

					cols = line.split(",");
					strLoginDeails = new ArrayList<String>();
					for (int j = 0; j < cols.length; j++) {
						strLoginDeails.add(cols[j]);

					}

					innerTestDataIterationMap = new HashMap<String, String>();
					for (int i = 0; i < strLoginDeails.size(); i++) {
						innerTestDataIterationMap.put(headerList.get(i), strLoginDeails.get(i));
					}

					sRow = sRow + currItr;
					outerTestDataMap.put(sRow, innerTestDataIterationMap);
					sRow = "Row";
					currItr++;
				}
			}

			for (int j = 0; j < outerTestDataMap.size(); j++) {
				sRow = sRow + (j + 1);
				HashMap<String, String> mapInnerkeyValue = outerTestDataMap.get(sRow);				
				sRow = "Row";

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return outerTestDataMap;
	}

}
