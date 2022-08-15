package com.valmart.projectName.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;


/*
 * Here we are handling the basic setup required to run tests. It will be extended by all test classes
 */
public class BaseClass {
	
	private static WebDriver driver = null;
	
	static final Logger logger = Logger.getLogger(BaseClass.class.getName());

	private static WebDriver getDriver() {
		return driver;
	}
	
	
	/*
	 * This method being used to setup the driver before start the test.
	 * @param browserType: Browser against which test need to run.
	 * @throws IOException
	 */

	private static void setDriver(String url) throws IOException {
		if (Utility.browserType.equalsIgnoreCase("chrome")) {
			driver = initChromeDriver(url);
		} else if (Utility.browserType.equalsIgnoreCase("firefox")) {
			driver = initFirefoxDriver(url);
		} else {
			logger.info("Launching Chrome as browser of choice..");
			driver = initChromeDriver(url);
		}
	}

	/*
	 * This method being used to setup the chrome driver.
	 * @return WebDriver object
	 */
	private static WebDriver initChromeDriver(String url) {
		WebDriver driver = null;
		logger.info("Launching google chrome with new profile..");
		WebDriverManager.chromedriver().setup();
		try {
		if(Utility.headless.equalsIgnoreCase("true")) {
			ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("--disable-dev-shm-usage"); 
            options.addArguments(Constants.WINDOW_SIZE);
            driver = new ChromeDriver(options);
		}else {
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while accessing Chrome Browser" + e.getMessage());
		}
		driver.navigate().to(url);
		return driver;
	}

	/*
	 * This method being used to setup the firefox driver.
	 * @return WebDriver object
	 */
	private static WebDriver initFirefoxDriver(String url) throws IOException {
		WebDriver driver = null;
		logger.info("Launching Firefox browser..");
		WebDriverManager.firefoxdriver().setup();
		try {
			if(Utility.headless.equalsIgnoreCase("true")) {
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				firefoxBinary.addCommandLineOptions("--headless");
				FirefoxOptions options = new FirefoxOptions();
				options.setBinary(firefoxBinary);
	            options.addArguments(Constants.WINDOW_SIZE);
	            driver = new FirefoxDriver(options);
			}else {
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			}
			}catch (Exception e) {
				e.printStackTrace();
				logger.error("Error while accessing Firefox Browser" + e.getMessage());
			}
		
		driver.navigate().to(url);
		return driver;
	}
		

	/*
	 * This method being used to call the setDriver method to setup driver based on the user browser input.
	 * @param browserType: Browser against which test need to run.
	 */
	public static WebDriver initializeTestBaseSetup(String url) {
		try {
			setDriver(url);
		    getDriver();

		} catch (Exception e) {
			logger.error("Error while initializing setup" + e.getMessage());
		}
		return driver;
	}
	
	/*
	 * This method is used to take the screenshot where ever there is a test fail in UI test run.
	 * @param screenshotName : Name of the image.
	 * @return Path : Path where Image need to be save.
	 */
	public static String getScreenshot(String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		String destination = null;
		if (driver != null) {
			File source = ts.getScreenshotAs(OutputType.FILE);
			destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
					+ ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
		}
		return destination;

	}

}
