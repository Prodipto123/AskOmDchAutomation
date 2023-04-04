package com.selenium.pageobjects;

import org.openqa.selenium.By;

public class LoginPage {
	
	BasePage basePage;	
	By userName = By.xpath(".//input[@id='username']");
	By passWord = By.xpath(".//input[@id='password']");
	By login = By.xpath(".//button[@name='login']");
	By loginverify = By.xpath("(.//strong[text()='prodipto123'])[1]");
	
	public LoginPage(BasePage basePage)
	{
		System.out.println("Login Page Initialized");
		this.basePage = basePage;
	}
	
	
	public void login(String strUserName, String strPassWord)  {
		
		basePage.getTextBoxObject().enterText(userName, strUserName, "UserName TextBox");
		basePage.getTextBoxObject().enterText(passWord, strPassWord, "Password TextBox");
		try {
			basePage.getButtonObject().clickButton(login, "Login Button");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	public String loginVerify() {
		
		return basePage.getLabelObject().getLabelText(loginverify, "Login Verification Text");
	}
}
