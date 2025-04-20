package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Utility class for reading configuration properties from the config.properties file.
 */

public class ConfigReader {

	
	private static Properties config;
	
	 /**
     * Loads the configuration properties from the config.properties file.
     * If the config is already loaded, it returns the existing instance.
     *
     * @return The Properties object containing the configuration.
     */
	public static Properties loadConfig() {
		if (config == null) {
			config = new Properties();
			try(FileInputStream fis = new FileInputStream("config.properties")){
				config.load(fis);
			} catch(IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load config.properties");
			}
				
		}
		return config;
		
	}
	
	/**
     * Returns the value of the property with the specified key from the loaded configuration.
     *
     * The key of the property to retrieve.
     * @return The value of the property, or null if the key is not found.
     */
	public static String getProperties(String key)
	{
		return loadConfig().getProperty(key);
	}
	
	
	
}
