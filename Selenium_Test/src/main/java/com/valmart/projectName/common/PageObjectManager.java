package com.valmart.projectName.common;

import org.openqa.selenium.WebDriver;

import com.valmart.projectName.pages.LoginPage;

/*
 * Here we are maintaining the singleton behavior of each page class
 */
public class PageObjectManager {

	private WebDriver driver;

	private LoginPage loginPage;


	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}
	
	/*
	 * Method use to control the restrict object creation of LoginPage object to 1
	 * @return LoginPage object
	 */
	public LoginPage getLoginPage() {

		if (loginPage == null) {
			loginPage = new LoginPage(driver);
		}	
		return loginPage;
	}
	

}
