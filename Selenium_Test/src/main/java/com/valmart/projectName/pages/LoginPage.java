package com.valmart.projectName.pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.valmart.projectName.common.BaseClass;
import com.valmart.projectName.common.Constants;
import com.valmart.projectName.common.Utility;

/*
 * This is object repository for Login Page, here we define all the locators and method required for the execute the test.
 */
public class LoginPage {

	static final Logger logger = Logger.getLogger(BaseClass.class.getName());

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Enter Email/Mobile number']")
	@CacheLookup
	private WebElement emailId;
	
	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	@CacheLookup
	private WebElement password;
	
	@FindBy(how = How.XPATH, using = "//button/span[text()='Login']")
	@CacheLookup
	private WebElement loginButton;
	

	/*
	 * Here we are using the locators for login to the application.
	 * 
	 * @param name: Login UserName, password: Login Password
	 */
	public void login(String name, String password) throws InterruptedException { 
		Thread.sleep(5000);
		if (Utility.browserType.equalsIgnoreCase("firefox")) {
			logger.info("Applictaion Loaded in firefox");
		} else {
			logger.info("Applictaion Loaded in chrome");
		}
		try {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.XPATH("//span[text()='Enter Email/Mobile number']")));
		emailId.sendKeys(name);
		Thread.sleep(2000);
		password.sendKeys(password);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		loginButton.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		//Make assertion here
		//Assert.assertEquals (exp,act);
		}catch (Exception e) {
			logger.error(e);
		}

	}
}