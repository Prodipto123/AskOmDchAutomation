package com.selenium.webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.selenium.framework.DriverManager;
import com.selenium.framework.SeleniumWrapper;

public class Link {
	SeleniumWrapper seleniumWrapper;
		
	public Link(SeleniumWrapper seleniumWrapper) {
		
		System.out.println("Link class initialized");
		this.seleniumWrapper = seleniumWrapper;
			
	}

	
	public void clickLink(By locator, String objectName) {
		
		try {
			seleniumWrapper.click(locator,objectName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
