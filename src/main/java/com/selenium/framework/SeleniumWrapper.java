package com.selenium.framework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.data.DataHelper;
import com.selenium.reporting.Reporter;

public class SeleniumWrapper {
	
	WebDriverWait webdriverWait = new WebDriverWait(DriverManager.getDriver(),DataHelper.timeOut20) ;
	
	public WebElement findElement(By by) {

		WebElement element = DriverManager.getDriver().findElement(by);
		return element;
	}

	public List<WebElement> findElements(By by) {

		return DriverManager.getDriver().findElements(by);

	}

	public void click(By locator, String objectName)  {

		try {
			webdriverWait.until(ExpectedConditions.elementToBeClickable(locator));
			
			DriverManager.getDriver().findElement(locator).click();
			System.out.println(objectName + " has been clicked ");
			Reporter.pass(objectName + " has been clicked ");
			Reporter.takeScreenShot();
			
			}catch(Exception e) {
				
				System.out.println(objectName + " has not been clciked");
				System.out.println("the message  " + e.getMessage());
				Reporter.fail(objectName + " has not been clicked ");
				Reporter.fail("Exception Message " + e.getMessage());
				Assert.fail();
			}


	}

	public void setText(By locator, String strText, String objectName) {
		
		try {
			webdriverWait.until(ExpectedConditions.elementToBeClickable(locator));
			DriverManager.getDriver().findElement(locator).sendKeys(strText);
			System.out.println("Added " + strText + " value to " + objectName);
			
		}catch(Exception e) {
			
			System.out.println(objectName + " not found ");
			System.out.println("the message  " + e.getMessage());
			Assert.fail();
		}
		
	}

	public void selecValUsingVisbleText(By locator,String strText, String objectName) {

		try {
			webdriverWait.until(ExpectedConditions.elementToBeClickable(locator));
			Select s = new Select( DriverManager.getDriver().findElement(locator));
			s.selectByVisibleText(strText);
			System.out.println("Selected " + strText + " from " + objectName);
			s = null;
		}
		catch(Exception e) {
			System.out.println(objectName + " not found ");
			System.out.println("the message  " + e.getMessage());
		}
	}
	
	public void selecValUsingIndex(By locator,int indx, String objectName, String strText)    {

		try {
			webdriverWait.until(ExpectedConditions.elementToBeClickable(locator));
			Select s = new Select( DriverManager.getDriver().findElement(locator));
			s.selectByIndex(indx);
			System.out.println("Selected " + strText + " from " + objectName);
			s = null;
		}
		catch(Exception e) {
			System.out.println(objectName + " not found ");
			System.out.println("the message  " + e.getMessage());
			
		}
	}
	
	
	public String getText(By locator, String objectName) {
		
		String text = "";
		try {
			 webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			 text = DriverManager.getDriver().findElement(locator).getText();
		}catch(Exception e) {
			System.out.println(objectName + " not found ");
			System.out.println("the message  " + e.getMessage());
		}
		
		return text;
	}

	// wait related thing
	public boolean verifyObject(By locator) {

		int counter = 0;
		boolean flagFound = false;
		List<WebElement> objList = DriverManager.getDriver().findElements(locator);
		try {
			if (objList.size() == 1) {
				flagFound = true;
				return flagFound;
			}

			while (objList.size() != 1 && counter <= 10) {

				Thread.sleep(2000);
				objList = DriverManager.getDriver().findElements(locator);
				if (objList.size() == 1) {
					flagFound = true;
					break;
				}
				counter++;
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return flagFound;
	}

	// wait related things
	// frame rrlated
	// window related

}
