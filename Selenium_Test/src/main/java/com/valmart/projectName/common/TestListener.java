package com.valmart.projectName.common;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

/*
 * Here we configured the listener to execute the task based on the test results. 
 */
public class TestListener implements ITestListener {
	
	static final Logger logger = Logger.getLogger(BaseClass.class.getName());

	/*
	 * Overriding onStart method for getting name of test run.
	 * @param ITestContext Object
	 */
	@Override
	public void onStart(ITestContext context) {

		logger.info("*** Test Suite " + context.getName() + " started ***");

	}

	/*
	 * Overriding onFinish method for getting name of test run completed and flushing the extent report instance.
	 * @param ITestContext Object
	 */
	@Override
	public void onFinish(ITestContext context) {
		logger.info(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getReports().flush();
	}

	/*
	 * Overriding onTestStart method for getting methods of test run.
	 * @param ITestResult Object
	 */
	@Override
	public void onTestStart(ITestResult result) {
		logger.info(("*** Running test method " + result.getMethod().getMethodName() + "-" + result.getMethod().getDescription() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName() + "-" + result.getMethod().getDescription());
	}

	/*
	 * Overriding onTestSuccess method for getting the status of the test run.
	 * @param ITestResult Object
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("*** Executed " + result.getMethod().getMethodName() + "-" + result.getMethod().getDescription() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	/*
	 * Overriding onTestFailure method for getting status of the test run, and take screenshot for UI test.
	 * @param ITestResult Object
	 */
	@Override
	public void onTestFailure(ITestResult result){

		logger.info("*** Test execution " + result.getMethod().getMethodName() + "-" + result.getMethod().getDescription() + " failed...");
		try {
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
		ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
			String screenshotPath = BaseClass.getScreenshot(result.getName());
			logger.info(screenshotPath);
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
		} catch (Exception e) {
			logger.error(e);
		}
		
	}

	/*
	 * Overriding onTestSkipped method for getting name of test which is skipped.
	 * @param ITestResult Object
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		logger.info("*** Test " + result.getMethod().getMethodName() + "-" + result.getMethod().getDescription() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	/*
	 * Overriding onTestFailedButWithinSuccessPercentage method for getting name of test run
	 * @param ITestResult Object
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info("*** Test failed but within percentage % " + result.getMethod().getMethodName() + "-" + result.getMethod().getDescription());

	}
 

}
