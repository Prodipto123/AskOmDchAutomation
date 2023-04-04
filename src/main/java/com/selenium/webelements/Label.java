package com.selenium.webelements;

import org.openqa.selenium.By;

import com.selenium.framework.SeleniumWrapper;

public class Label {
	
	SeleniumWrapper seleniumWrapper;
	public Label(	SeleniumWrapper seleniumWrapper) {
		
		this.seleniumWrapper = seleniumWrapper;
		
	}
	
	public String getLabelText(By locator, String objectName) {
		
		return seleniumWrapper.getText(locator, objectName);
	}

}
