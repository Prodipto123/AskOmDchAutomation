package com.selenium.pageobjects;

import com.selenium.framework.SeleniumWrapper;
import com.selenium.webelements.Button;
import com.selenium.webelements.Dropdown;
import com.selenium.webelements.Label;
import com.selenium.webelements.Link;
import com.selenium.webelements.TextBox;

public class BasePage {
	
	Link olink ;
	TextBox otextbox;
	Button obutton;
	SeleniumWrapper seleniumWrapper;
	Dropdown odropdown ;
	Label olabel;
	
	public BasePage() {
		
		System.out.println("BasePage initialized");
		
		seleniumWrapper = new SeleniumWrapper();
		olink = new Link(seleniumWrapper);	
		otextbox = new TextBox(seleniumWrapper);
		obutton = new Button(seleniumWrapper);
		odropdown = new Dropdown(seleniumWrapper);
		olabel = new Label(seleniumWrapper);
		
	}
	
	public Link getLinkObject() {
	
		return olink;
	
	}
	
	public TextBox getTextBoxObject() {
		
		
		return otextbox;
		
	}
	
	public SeleniumWrapper getSeleniumWrapperObj() {
		return seleniumWrapper;
	}
	
	public Button getButtonObject() {
		return obutton;
	}
	
	public Dropdown getDrpdownObject() {
		return odropdown;
	}
	
	public Label getLabelObject() {
		return olabel;
	}
	
	
	

}
