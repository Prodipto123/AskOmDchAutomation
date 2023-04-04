package com.selenium.pageobjects;

import org.openqa.selenium.By;

import com.selenium.reporting.Reporter;
import com.selenium.webelements.Link;
import com.selenium.webelements.TextBox;

public class LandingPage {
	
	// identifier - xpath , id , class name etc 
	// WebElement 
	// methods to perform actions on the web element 
	BasePage basePage;
	
	By accountLink = By.xpath("(.//a[text()='Account'])[1]");

	public LandingPage(BasePage basePage) {
		
		System.out.println("Landing Page Initialized");		
		this.basePage = basePage;
	}
		
	
	public void clickAccountLink() {
		
		System.out.println("clickAccountLink method calling" );
		
		basePage.getLinkObject().clickLink(accountLink,"Account Link");
		Reporter.info("Click Account Link");		
		
	}
	
	
	
}
