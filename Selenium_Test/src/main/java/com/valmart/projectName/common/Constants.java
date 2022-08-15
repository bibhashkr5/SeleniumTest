package com.valmart.projectName.common;

/*
 * Here defining all the common variables required to run the test throughout
 */
public class Constants {

	/*
	 * Parameters used for UI
	 */	
	public static String CDR = System.getProperty("user.dir") + "/src/main/resources/";
	public static String URL = Utility.getUrl();
	public static String CLIENT_URL = Utility.getClientUrl();
	public static String API_URL = Utility.getApiUrl();
	public static String WINDOW_SIZE = "window-size=1200x1800";
	
	
	/*
	 * Parameters used for User-Login
	 */
	public static String UserName = "test-user@gmail.com";
	public static String Password = "12345";
	
	
}
