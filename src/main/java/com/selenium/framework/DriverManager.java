package com.selenium.framework;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.helper.PropertiesHelper;

public class DriverManager {
	
	
	private static ThreadLocal<WebDriver> localDriverThread = new ThreadLocal<WebDriver>();
	String currentDir = System.getProperty("user.dir");
	private WebDriver driver;
	OptionsManager optionsManager = new OptionsManager();
	PropertiesHelper driverProprties = new PropertiesHelper("Driver.properties");
	
	
	public static WebDriver getDriver() {
		
		return localDriverThread.get();
	}

	public void startDriver() {
		
		
		String filePath = currentDir  + "\\Drivers\\";	
		String driverPath=null;
		
		String browserName = driverProprties.getProperty("browserName").toLowerCase();
		if (browserName.equals("chrome")) {
		
			if (Boolean.parseBoolean(driverProprties.getProperty("remote"))) {
				// remote run
			 //	createRemoteDriver(browserName);
				
			} else {
				// local run
				driverPath = filePath + "chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				//WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(optionsManager.getChromeOptions());
				localDriverThread.set(driver);

			}

		}
		
		
	}
	
	

}
