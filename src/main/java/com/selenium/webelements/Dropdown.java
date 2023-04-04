package com.selenium.webelements;

import org.openqa.selenium.By;

import com.selenium.framework.SeleniumWrapper;

public class Dropdown {

	SeleniumWrapper seleniumWrapper;

	public Dropdown(SeleniumWrapper seleniumWrapper) {
		System.out.println("Dropdown class initialized");
		this.seleniumWrapper = seleniumWrapper;
	}

	public void selectByVisibleText(By locator, String valueToSelect, String objectName) {
		
		seleniumWrapper.selecValUsingVisbleText(locator, valueToSelect, objectName);

	}

	public void selectByIndex(By locator, int index, String objectName, String valueToSelect ) {
		
		seleniumWrapper.selecValUsingIndex(locator, index, objectName, valueToSelect);
	}

}
