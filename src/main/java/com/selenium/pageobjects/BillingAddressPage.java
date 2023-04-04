package com.selenium.pageobjects;

import org.openqa.selenium.By;

public class BillingAddressPage {

	By addressLink = By.xpath(".//a[text()='Addresses']");
	By billingAddrAdd = By.xpath("(.//a[text()='Add'])[1]");
	By shippingAddrAdd = By.xpath("(.//a[text()='Add'])[2]");
	By fistName = By.xpath(".//input[@id='billing_first_name']");
	By lastName = By.xpath(".//input[@id='billing_last_name']");
	By country = By.xpath(".//select[@id='billing_country']");
	By streetAddr = By.xpath(".//input[@id='billing_address_1']");
	By townCity = By.xpath(".//input[@id='billing_city']");
	By state = By.xpath(".//select[@id='billing_state']");
	By pin = By.xpath(".//input[@name='billing_postcode']");
	By saveAddr = By.xpath(".//button[@value='Save address']");
	BasePage basePage;

	public BillingAddressPage(BasePage basePage) {
		this.basePage = basePage;

	}
	
	public void clickBillingAddressLink() {
		
		basePage.getLinkObject().clickLink(addressLink, "Address Link");
		basePage.getLinkObject().clickLink(billingAddrAdd, "Billing Address Link");
		
	}

	public void addBillingAddress(String strFirstName, String strLastName, String strCounty, String strStreetAddr,
			String strCity, String strState, String strZip) {

		try {
			basePage.getTextBoxObject().enterText(fistName, strFirstName, "First Name TextBox");
			basePage.getTextBoxObject().enterText(lastName, strLastName, "Last Name TextBox");
			basePage.getTextBoxObject().enterText(streetAddr, strStreetAddr, "Street Address TextBox");
			basePage.getTextBoxObject().enterText(townCity, strCity, "City TextBox");
			basePage.getTextBoxObject().enterText(pin, strZip, "Zip TextBox");
			basePage.getDrpdownObject().selectByVisibleText(country, strCounty, "Country Dropdown");
			Thread.sleep(2000);
			basePage.getDrpdownObject().selectByVisibleText(state, strState, "State Dropdown");

			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
