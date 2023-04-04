package com.selenium.base;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.selenium.framework.DriverManager;

import com.selenium.helper.ExcelHelper;
import com.selenium.helper.PropertiesHelper;
import com.selenium.pageobjects.BasePage;
import com.selenium.reporting.ExtentManager;
import com.selenium.reporting.Reporter;
import com.selenium.reporting.VideoRecording;

public class BaseTest {

	DriverManager driverManager;
	String testFilePath;
	protected List<Map<String, String>> testDataMap;
	protected List<Map<String, String>> loginDataMap;
	ExcelHelper exclDataFile;
	PropertiesHelper testMapping;
	PropertiesHelper driverProperties;
	PropertiesHelper nodeMapping;
	protected SoftAssert softAssert;
	boolean enabledVideoFlag = false;
	boolean testExtentFlag = true;

	String loginFilePath = "\\InputData\\LoginData\\Login.xlsx";
	String nodeMappingPath = "NodeEnvironmentMapping.properties";
	String outputDataPath = "\\OutputData\\";
	String dir = System.getProperty("user.dir");
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	String reportPath = dir + outputDataPath + "Report_" + timeStamp;
	int counter;
	String lastMethodName = "";
	String currMethodName = "";

	@BeforeSuite
	public void beforeSuite(ITestContext textContext) {

		try {
			counter = 1;
			// textContext.getName()
			FileUtils.forceMkdir(new File(reportPath));

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		ExtentManager.createExtentReports(reportPath, "ExecutionReport.html");
	}

	@BeforeMethod
	public void beforeMethod(Method methodName) {
		// which browser execute - read driver.properties to get browser name -
		// completed
		// read test data - it will passed to test in order to login credentials and url
		// detection - completed
		// navigate to url - completed
		// Initalize log4j -
		// Extent Report
		// exception handling

		// start webdriver

//		if( methodName.getName().equalsIgnoreCase(lastMethodName)) {
//			counter++;
//		}else {
//			counter = 1;
//		}
//		currMethodName = methodName.getName() + "Iteration" + counter;

		// ExtentManager.startTest(currMethodName, "");
		System.out.println("before method started");

		driverProperties = new PropertiesHelper("TestDataMapping.properties");
		enabledVideoFlag = Boolean.parseBoolean(driverProperties.getProperty("enabledVideo"));

		//if (!testExtentFlag) {
			getCurrentMethodName(methodName);
		//}

		String testMethodFolder = reportPath + "\\" + methodName.getName() + "Iteration" + counter;
		Reporter.testMethodReportFolder = testMethodFolder;
		if (!testExtentFlag) {
			ExtentManager.startTest(currMethodName, "");
		}

		try {
			FileUtils.forceMkdir(new File(testMethodFolder));

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		if (enabledVideoFlag) {

			try {
				VideoRecording.startRecord(methodName.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driverManager = new DriverManager();
		driverManager.startDriver();

		ExcelHelper exclLoginData = new ExcelHelper(loginFilePath);
		loginDataMap = exclLoginData.getExclData();
		int a = 10;

	}

	@DataProvider(name = "Passing List Of Maps")
	public Iterator<Object[]> createDataforTest3(Method methodName) {

		Collection<Object[]> dp = null;
		try {
			System.out.println("Data Provider Method Started");
			getCurrentMethodName(methodName);
			ExtentManager.startTest(currMethodName, "");
			testExtentFlag = true;
			testMapping = new PropertiesHelper("TestDataMapping.properties");
			testFilePath = testMapping.getProperty(methodName.getName());
			// testFilePath = testMapping.getProperty("ABCD");

			ExcelHelper exclDataFile = new ExcelHelper(testFilePath);
			testDataMap = exclDataFile.getExclData();

			dp = new ArrayList<Object[]>();
			for (Map<String, String> map : testDataMap) {
				dp.add(new Object[] { map });
			}
			return dp.iterator();
		} catch (Exception e) {

			System.out.println(e.getMessage());
			Reporter.fail("e.getMessage()");
			Assert.fail();

		}

		return dp.iterator();
	}

	public List<String> getLoginInformation(String strOU, String strEnv) {

		boolean flagFound = false;
		List<String> login = null;
		String strNode;

		for (int i = 0; i < loginDataMap.size(); i++) {

			if (loginDataMap.get(i).get("OU").trim().equalsIgnoreCase(strOU)
					&& loginDataMap.get(i).get("OU").trim().equalsIgnoreCase(strOU)) {

				login = new ArrayList<String>();
				login.add(loginDataMap.get(i).get("UserName").trim());
				login.add(loginDataMap.get(i).get("Password").trim());
				strNode = loginDataMap.get(i).get("Node").trim();
				String strUrl = getAppURL(strNode);
				login.add(strUrl);
				flagFound = true;
				break;
			}
		}

		if (!flagFound) {

			System.out.println("not value found ");

		}

		return login;
	}

	public String getAppURL(String nodeValue) {

		nodeMapping = new PropertiesHelper(nodeMappingPath);
		return nodeMapping.getProperty(nodeValue);

	}

	public void invokeAppURL(String url) {

		DriverManager.getDriver().navigate().to(url);
		Reporter.info("Url Invoked");
	}

	@AfterMethod
	public void afterMethod(Method methodName) {

		System.out.println("after method started");
		lastMethodName = methodName.getName().trim();
		testExtentFlag = false;
		if (enabledVideoFlag) {

			try {
				VideoRecording.stopRecord();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DriverManager.getDriver().quit();

	}

	@AfterSuite
	public void afterSuite(ITestContext context) {

		System.out.println("after suite started");
		ExtentManager.endReport();
	}

	public void getCurrentMethodName(Method methodName) {

		if (methodName.getName().equalsIgnoreCase(lastMethodName)) {
			counter++;
		} else {
			counter = 1;
		}
		currMethodName = methodName.getName() + "Iteration" + counter;
		// return currMethodName;
	}

}
