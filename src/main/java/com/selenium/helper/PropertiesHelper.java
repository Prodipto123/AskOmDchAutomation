package com.selenium.helper;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import com.selenium.reporting.Reporter;

public class PropertiesHelper {

	String dir = System.getProperty("user.dir");
	FileInputStream fis;
	Properties property;
	
	
	public PropertiesHelper(String propertyFileName) {

		try {

			fis = new FileInputStream(dir + "\\InputData\\ConfigData\\" + propertyFileName);
			property = new Properties();
			property.load(fis);

		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while reading the File");
			e.printStackTrace();
		}

	}

	public String getProperty(String propertyName) {
	
		String propertyValue= "";
	 try {	
		
		propertyValue = property.getProperty(propertyName);
		return propertyValue;
	 }catch(Exception e) {
		 
		 Reporter.fail(" getProperty() | Not able to find the Key Or Return wrong file Name | Exception Message " + e.getMessage() );
		// Assert.fail();
		 return propertyValue = "";
	 }

	}
}
