package utils;

import java.util.Properties;


public class Constants {
	static ReadPropertyFile bundle =ReadPropertyFile.getInstance();
	
	public static String DRIVER = ReadPropertyFile.getInstance().getPropConst().getProperty("driver");
	public static String DRIVER_URL = ReadPropertyFile.getInstance().getPropConst().getProperty("driverURL");
	public static String USERNAME = ReadPropertyFile.getInstance().getPropConst().getProperty("userName");
	public static String PASSWORD = ReadPropertyFile.getInstance().getPropConst().getProperty("password");
	
	public static String SUCCESS =  "Success";
	public static String FAILURE =  "Failure";
	public static String STATUS =  "Status";
	public static String EXCEPTION =  "Exception";
	public static String MESSAGE =  "Message";
	
}
