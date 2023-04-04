package com.selenium.framework;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {

	// Get Chrome Options
	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("--disable-popup-blocking");
		// options.addArguments("--incognito");
		return options;
	}

}
