package com.selenium.webelements;

import org.openqa.selenium.By;

import com.selenium.framework.SeleniumWrapper;

public class Button {
	
	SeleniumWrapper seleniumWrapper;
	public Button(	SeleniumWrapper seleniumWrapper) {
		
		this.seleniumWrapper = seleniumWrapper;
		
	}
	
	public void clickButton(By locator, String objectName)  {
		
		try {
			seleniumWrapper.click(locator, objectName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
		}
	}

}
