package com.core.framework.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	public final static String PROPERTY_FILENAME = System.getProperty("user.dir")+File.separator+"config"+File.separator+"OR.properties";

	private Properties property_reader = new Properties();

	/**
	 * Loads the properties file
	 */
	public PropertyFileReader() {
		try {
			property_reader.load(new FileInputStream(PROPERTY_FILENAME));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		assert !property_reader.isEmpty();
	}

	/**
	 * returns the value of the property denoted by key
	 */
	
	public String getORProperty(final String key) { 
		String property = property_reader.getProperty(key);
		return property != null ? property.trim() : property;
	}

}
