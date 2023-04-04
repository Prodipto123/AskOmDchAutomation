package com.selenium.webelements;

import org.openqa.selenium.By;

import com.selenium.framework.DriverManager;
import com.selenium.framework.SeleniumWrapper;

public class TextBox {
	
	SeleniumWrapper seleniumWrapper;
	
	public TextBox(SeleniumWrapper seleniumWrapper)
	{
		System.out.println("Textbox class initialized");
		this.seleniumWrapper = seleniumWrapper;		
	}
	
	
	
	public void enterText(By locator,String strtext, String objectName) {
		
		seleniumWrapper.setText(locator, strtext, objectName);
		
	}

}
