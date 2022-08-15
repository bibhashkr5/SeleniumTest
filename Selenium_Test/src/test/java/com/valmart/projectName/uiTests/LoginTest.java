package com.valmart.projectName.uiTests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.valmart.projectName.common.BaseClass;
import com.valmart.projectName.common.PageObjectManager;
import com.valmart.projectName.common.Constants;
import com.valmart.projectName.common.Utility;
import com.valmart.projectName.pages.LoginPage;

public class LoginTest {

	WebDriver driver;
	PageObjectManager pageObjectManager;
	LoginPage loginPage;
	
	static final Logger logger = Logger.getLogger(BaseClass.class.getName());

	@Test(description="Verify that user is able to login successfully")
	public void testScript() {
		try {
		driver = BaseClass.initializeTestBaseSetup(Constants.URL);
		pageObjectManager = new PageObjectManager(driver);
		loginPage = pageObjectManager.getLoginPage();
		loginPage.login(Constants.UserName, Constants.Password);
		logger.info("User successfully log-in to application");
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}

	
	/*
	 * After class is used to close the driver after all test are completed.
	 */
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
