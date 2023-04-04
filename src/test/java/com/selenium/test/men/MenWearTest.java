package com.selenium.test.men;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.selenium.base.BaseTest;
import com.selenium.pageobjects.BasePage;
import com.selenium.pageobjects.BillingAddressPage;
import com.selenium.pageobjects.LandingPage;
import com.selenium.pageobjects.LoginPage;
import com.selenium.reporting.Reporter;


public class MenWearTest extends BaseTest {

	@Test(dataProvider = "Passing List Of Maps", dataProviderClass = BaseTest.class)
	//
	public void addMenWear(Map<String, String> map) {

		// Account login - 1st function
		List<String> lsLoginInformation;
		String firstName, lastName, country, streetAddr,town, state , pin; 

		try {
			System.out.println("started");
			Reporter.info("Test Started ");
			
			System.out.println(map);

			String strOU = map.get("OU1").trim();
			String strEnv = map.get("Environment1").trim();

			lsLoginInformation = getLoginInformation(strOU, strEnv);			
			invokeAppURL(lsLoginInformation.get(2));
			
			BasePage basePage = new BasePage();
			LandingPage landingPage = new LandingPage(basePage);
			LoginPage loginPage = new LoginPage(basePage);
			BillingAddressPage billingAddrPage = new BillingAddressPage(basePage);
			
			// click account link
			landingPage.clickAccountLink();
			
			
			// login
			loginPage.login(lsLoginInformation.get(0).trim(), lsLoginInformation.get(1).trim());
			//String actualString = loginPage.loginVerify();
			Reporter.info("Login method  Started ");
			
			Thread.sleep(2000);
			// add billing address 
//			firstName = map.get("FirstName").trim();
//			lastName = map.get("LastName").trim();
//			country = map.get("Country").trim();
//			streetAddr = map.get("StreetAddress").trim();
//			town = map.get("TownCity").trim();
//			state  = map.get("State").trim();
//			pin = map.get("Pin").trim();
//			billingAddrPage.clickBillingAddressLink();
//			billingAddrPage.addBillingAddress(firstName, lastName, country, streetAddr, town, state, pin);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
}
