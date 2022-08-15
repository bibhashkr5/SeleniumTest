package com.valmart.projectName.common;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/*
 * Here we are configuring the extent report for reporting.
 */
public class ExtentManager {

	private static ExtentReports extent;
	private static ExtentHtmlReporter htmlReporter;

	
	/*
	 * This method is used to setting up the config changes for extent report.
	 * @return ExtentReports Object
	 */
	public static ExtentReports getReports() {
		String cwd = System.getProperty("user.dir");
		htmlReporter = new ExtentHtmlReporter(new File(cwd + "/Reports/TestReport.html"));
		htmlReporter.loadXMLConfig(new File(cwd + "/extent-config.xml"), true);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}

}
