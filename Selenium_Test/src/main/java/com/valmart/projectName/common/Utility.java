package com.valmart.projectName.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/*
 * Here we have defined all the common methods, which will be in use throughout the project.
 */
public class Utility {

	static final Logger logger = Logger.getLogger(BaseClass.class.getName());

	public static String environment;
	public static String headless;
	public static String browserType;
	public static String mysqlUrl;
	public static String userName;
	public static String password;

	/*
	 * Getting the url from the config file based on the environment passed during
	 * runtime.
	 * 
	 * @return String
	 */
	public static String getUrl() {
		String url = "";
		try {
			getConfigProperty();

			if (environment.equalsIgnoreCase(Environment.DEV.toString())) {
				url = readUrlJson("dev_url");

			} else if (environment.equalsIgnoreCase(Environment.LOCAL.toString())) {
				url = readUrlJson("local_url");
			} else {
				url = readUrlJson("stg_url");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return url;
	}

	/*
	 * Getting the clienturl from the config file based on the environment passed
	 * during runtime.
	 * 
	 * @return String
	 */
	public static String getClientUrl() {
		String clientUrl = "";
		try {
			getConfigProperty();

			if (environment.equalsIgnoreCase(Environment.DEV.toString())) {
				clientUrl = readUrlJson("dev_client_url");
			} else if (environment.equalsIgnoreCase(Environment.LOCAL.toString())) {
				clientUrl = readUrlJson("local_client_url");
			} else {
				clientUrl = readUrlJson("stg_client_url");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return clientUrl;

	}

	/*
	 * Getting the apiUrl from the config file based on the environment passed
	 * during runtime.
	 * 
	 * @return String
	 */
	public static String getApiUrl() {
		String apiUrl = "";
		try {
			getConfigProperty();
			if (environment.equalsIgnoreCase(Environment.DEV.toString())) {
				apiUrl = readUrlJson("dev_api_url");

			} else if (environment.equalsIgnoreCase(Environment.LOCAL.toString())) {
				apiUrl = readUrlJson("local_api_url");
			} else {
				apiUrl = readUrlJson("stg_api_url");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return apiUrl;
	}

	/*
	 * This method is use to read base urls from a file
	 * 
	 * @param environment Url
	 * 
	 * @return String
	 */
	public static String readUrlJson(String envUrl) {
		String url = "";
		try {
			Object obj = readJsonFile("base_urls.json");
			JSONObject jo = (JSONObject) obj;
			url = (String) jo.get(envUrl);
		} catch (IOException e) {
			logger.error(e);
		}
		return url;
	}

	/*
	 * This method is use to read Database details from a file
	 */
	public static void readDbDetails(String env) {
		try {
			environment = env;
			getConfigProperty();
			Object obj = Utility.readJsonFile("database.json");
			JSONObject jo = (JSONObject) obj;
			JSONObject dbDetails = (JSONObject) jo.get(environment);
			mysqlUrl = (String) dbDetails.get("mysqlUrl");
			userName = (String) dbDetails.get("userName");
			password = (String) dbDetails.get("password");
		} catch (IOException e) {
			logger.error(e);
		}

	}

	/*
	 * This method is use to read the config properties
	 */
	public static void getConfigProperty() throws IOException {
		Properties prop = readPropertyFile("config.properties");
		if (environment == null) {
			environment = prop.getProperty("env");
		} else {
			logger.info("Environment is already Set as " + environment);
		}
		cookieValue = prop.getProperty("cookies");
		headless = prop.getProperty("headless");
		browserType = prop.getProperty("browserType");
		testRunName = prop.getProperty("testRunName");

	}

	/*
	 * Reading the property file based on the file passed.
	 * 
	 * @rerun Properties Object
	 */
	public static Properties readPropertyFile(String filePath) throws IOException {
		Properties prop = null;
		try {
			prop = new Properties();
			FileInputStream fileName = new FileInputStream(Constants.CDR + filePath);

			prop.load(fileName);

		} catch (FileNotFoundException e) {
			logger.error(e);
		}
		return prop;
	}

	/*
	 * Reading the Josn file based on the file passed.
	 * 
	 * @rerun Object class object.
	 */
	public static Object readJsonFile(String filePath) throws IOException {
		Object obj = null;
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader(Constants.CDR + filePath);
			obj = jsonParser.parse(reader);

		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (ParseException pr) {
			logger.error(pr);
		}
		return obj;
	}

	/*
	 * Wait statement for 1000 Millisecond
	 */

	public static void waitStatement() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}
}
