package utils;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Utility class for managing WebDriver instances and WebDriverWait.
 */

public class BrowserFactory {

	private static WebDriver driver;
	private static WebDriverWait wait;
	private static Properties config = ConfigReader.loadConfig();

	/**
	 * Returns the current WebDriver instance. If no instance exists, it creates one
	 * based on the browser specified in the config.properties file.
	 *
	 * @return The WebDriver instance.
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			String browser = config.getProperty("browser");
			switch (browser.toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}

		return driver;
	}

	/**
	 * Returns the WebDriverWait instance. If no instance exists, it creates one
	 * with a default timeout of 10 seconds.
	 *
	 * @return The WebDriverWait instance.
	 */
	public static WebDriverWait getWait() {

		if (wait == null) {
			wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		}
		return wait;
	}

	/**
	 * Quits the current WebDriver instance and sets the driver and wait to null.
	 */
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
			wait = null;
		}
	}

}
