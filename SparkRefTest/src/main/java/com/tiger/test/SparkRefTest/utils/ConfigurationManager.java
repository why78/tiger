/**
 *
 * Project Name:	hbec-platform-storm-tools
 * File Name:	QueueConsumer.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2016年1月27日
 * Version:		1.0
 * Remark：
 */
package com.tiger.test.SparkRefTest.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author WangHuiyuan
 *
 */
public class ConfigurationManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);

	private static Properties properties = null;

	private static String license;

	private final static int RECURSION_LIMIT = 9;

	/****************************************************
	 * @methode getProperty
	 * @note
	 * @param property
	 * @return
	 */
	public static String getProperty(String property) {
		if (properties == null) {
			loadConfig(null);
		}
		return properties.getProperty(property);
	}
	
	public static String getProperty(String property, String defaultValue) {
		if(getProperty(property) == null)
			return defaultValue;
		return getProperty(property);
	}
	
	/**
	 * *********************************************
	* @Title: getIntProperty
	* @Description: Get a configuration property as an integer
	* @param property: the name of the property
	* @return the value of the property. <code>0</code> is returned if the
	*         property does not exist. To differentiate between this case and
	*         when the property actually is zero, use <code>getProperty</code>.
	 */
	public static int getIntProperty(String property){
		return getIntProperty(property, 0);
	}

	/****************************************************
	 * Get a configuration property as an integer
	 * 
	 * @param property
	 *            the name of the property
	 * @param defaultValue
	 * 			the default value is returned if the property does not exist.
	 * 
	 * @return the value of the property. The default value is returned if the
	 *         property does not exist. To differentiate between this case and
	 *         when the property actually is zero, use <code>getProperty</code>.
	 */
	public static int getIntProperty(String property, int defaultValue) {
		if (properties == null) {
			loadConfig(null);
		}

		String stringValue = properties.getProperty(property);
		int intValue = defaultValue;

		if (stringValue != null) {
			try {
				intValue = Integer.parseInt(stringValue.trim());
			} catch (NumberFormatException e) {
				logger.error("Getting property[" + property + "] occurred errors: ", e);
			}
		}

		return intValue;
	}

	/****************************************************
	 * Get a configuration property as a boolean. True is indicated if the value
	 * of the property is <code>TRUE</code> or <code>YES</code> (case
	 * insensitive.)
	 * 
	 * @param property
	 *            the name of the property
	 * 
	 * @return the value of the property. <code>false</code> is returned if the
	 *         property does not exist. To differentiate between this case and
	 *         when the property actually is false, use <code>getProperty</code>
	 *         .
	 */
	public static boolean getBooleanProperty(String property) {
		return getBooleanProperty(property, false);
	}

	/****************************************************
	 * Get a configuration property as a boolean, with default. True is
	 * indicated if the value of the property is <code>TRUE</code> or
	 * <code>YES</code> (case insensitive.)
	 * 
	 * @param property
	 *            the name of the property
	 * 
	 * @param defaultValue
	 *            value to return if property is not found.
	 * 
	 * @return the value of the property. <code>default</code> is returned if
	 *         the property does not exist. To differentiate between this case
	 *         and when the property actually is false, use
	 *         <code>getProperty</code>.
	 */
	public static boolean getBooleanProperty(String property,
			boolean defaultValue) {
		if (properties == null) {
			loadConfig(null);
		}

		String stringValue = properties.getProperty(property);

		if (stringValue != null) {
			stringValue = stringValue.trim();
			return stringValue.equalsIgnoreCase("true")
					|| stringValue.equalsIgnoreCase("yes");
		} else {
			return defaultValue;
		}
	}

	/****************************************************
	 * Returns an enumeration of all the keys in the DSpace configuration
	 * 
	 * @return an enumeration of all the keys in the DSpace configuration
	 */
	public static Enumeration propertyNames() {
		if (properties == null)
			loadConfig(null);

		return properties.propertyNames();
	}

	/****************************************************
	 * Get the site-wide default license that submitters need to grant
	 * 
	 * @return the default license
	 */
	public static String getDefaultSubmissionLicense() {
		if (properties == null) {
			loadConfig(null);
		}

		return license;
	}

	/****************************************************
	 * Get the path for the news files.
	 * 
	 */
	public static String getNewsFilePath() {
		String filePath = ConfigurationManager.getProperty("dspace.dir")
				+ File.separator + "config" + File.separator;

		return filePath;
	}

	/****************************************************
	 * Writes license to a text file.
	 * 
	 * @param news
	 *            the text to be written to the file.
	 */
	public static void writeLicenseFile(String newLicense) {
		String licenseFile = getProperty("dspace.dir") + File.separator
				+ "config" + File.separator + "default.license";
		try {
			// write the news out to the appropriate file
			FileOutputStream fos = new FileOutputStream(licenseFile);
			OutputStreamWriter osr = new OutputStreamWriter(fos, "UTF-8");
			PrintWriter out = new PrintWriter(osr);
			out.print(newLicense);
			out.close();
		} catch (IOException e) {
			logger.error("", e);
		}

		license = newLicense;
	}

	private static File loadedFile = null;

	/****************************************************
	 * Return the file that configuration was actually loaded from. Only returns
	 * a valid File after configuration has been loaded.
	 * 
	 * @return File naming configuration data file, or null if not loaded yet.
	 */
	public static File getConfigurationFile() {
		// in case it hasn't been done yet.
		loadConfig(null);

		return loadedFile;
	}

	/****************************************************
	 * Load the DSpace configuration properties. Only does anything if
	 * properties are not already loaded. Properties are loaded in from the
	 * specified file, or default locations.
	 * 
	 * @param configFile
	 *            The <code>dspace.cfg</code> configuration file to use, or
	 *            <code>null</code> to try default locations
	 */
	
	public static void loadConfig(String configFile) {
		if (properties != null && configFile == null)
			return;
		InputStream is = null;
		try {

			is = ConfigurationManager.class.getClassLoader().getResourceAsStream(configFile);
			loadedFile = new File(configFile);

			if (is == null) {
				throw new RuntimeException("Cannot find " + configFile);
			} else {
				if (properties == null) {
					properties = new Properties();
				}
				properties.load(is);

				// walk values, interpolating any embedded references.
				for (Enumeration pe = properties.propertyNames(); pe
						.hasMoreElements();) {
					String key = (String) pe.nextElement();
					String value = interpolate(key, 1);
					if (value != null)
						properties.setProperty(key, value);
				}
			}

			is.close();

		} catch (IOException e) {
			throw new RuntimeException("Cannot find " + configFile);
		}
	}
	
	private static Map<String,Object> mapAllConfig = null;
	
	/**
	 * *********************************************
	* @Title: getAllConfig
	* @Description: Get the map included all configurations
	* @return Type of map's value includes: int, double, boolean 
	 */
	public static Map<String,Object> getAllConfig(){
		if(mapAllConfig != null && mapAllConfig.size() > 0)
			return mapAllConfig;
		mapAllConfig = new HashMap<String,Object>();
		if (properties == null) {
			loadConfig(null);
		}
		String strKey;
		String strValue;
		boolean isInt = false;
		boolean isDouble = false;
		boolean isBool = false;
		boolean isNull = false;
		for(Object key : properties.keySet()){
			strKey = (String)key;
			strValue = properties.getProperty(strKey);
			isInt = match(intRegex, strValue);
			if(isInt){
				mapAllConfig.put(strKey, Integer.parseInt(strValue));
				continue;
			}
			isDouble = match(doubleRegex, strValue);
			if(isDouble){
				mapAllConfig.put(strKey, Double.parseDouble(strValue));
				continue;
			}
			isBool = match(boolRegex, strValue);
			if(isBool){
				mapAllConfig.put(strKey, Boolean.parseBoolean(strValue));
				continue;
			}
			isNull = match(nullRegex, strValue);
			if(isNull){
				mapAllConfig.put(strKey, null);
				continue;
			}
			mapAllConfig.put(strKey, strValue);
		}
		
		return mapAllConfig;
	}
	
	/**
	 * *********************************************
	* @Title: get
	* @Description: Get object value by key 
	* @param key
	* @return Object's type includes: int, double, boolean
	 */
	public static Object get(String key){
		return getAllConfig().get(key);
	}
	

	/****************************************************
	 * Recursively interpolate variable references in value of property named
	 * "key".
	 * 
	 * @return new value if it contains interpolations, or null if it had no
	 *         variable references.
	 */
	private static String interpolate(String key, int level) {
		if (level > RECURSION_LIMIT)
			throw new IllegalArgumentException(
					"ConfigurationManager: Too many levels of recursion in configuration property variable interpolation, property="
							+ key);
		String value = (String) properties.get(key);
		int from = 0;
		StringBuffer result = null;
		while (from < value.length()) {
			int start = value.indexOf("${", from);
			if (start >= 0) {
				int end = value.indexOf("}", start);
				if (end < 0)
					break;
				String var = value.substring(start + 2, end);
				if (result == null)
					result = new StringBuffer(value.substring(from, start));
				else
					result.append(value.substring(from, start));
				if (properties.containsKey(var)) {
					String ivalue = interpolate(var, level + 1);
					if (ivalue != null) {
						result.append(ivalue);
						properties.setProperty(var, ivalue);
					} else
						result.append((String) properties.getProperty(var));
				} else {
					System.out
							.println("Interpolation failed in value of property \""
									+ key
									+ "\", there is no property named \""
									+ var + "\"");
				}
				from = end + 1;
			} else
				break;
		}
		if (result != null && from < value.length())
			result.append(value.substring(from));
		return (result == null) ? null : result.toString();
	}
	
	// Regex ///////////////////////////////////////////////////////////////////
	
	private static final String intRegex = "\\d+";
	private static final String doubleRegex = "\\d+\\.\\d+";
	private static final String boolRegex = "(true|false)";
	private static final String nullRegex = "null";
	
	public static boolean match(String regex,String matchStr){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(matchStr);
		boolean b= matcher.matches();
		return b;
		
	}
}
