package com.core.framework.baseClass;
import java.io.File; 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BaseDriverSetup {

	public WebDriver driver = null;

	public  WebDriver setup(final String BROWSER) throws Exception {
		{
			if(driver == null)
			{
				if (BROWSER.contentEquals("firefox")){
					FirefoxOptions options = new FirefoxOptions();
					options.setProfile(new FirefoxProfile());
					options.addPreference("dom.webnotifications.enabled", false);
					//Set system property and GeckoDriver Path
					System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+File.separator+"geckodriver");
					//Then create a new instance on firefox driver
					driver = new FirefoxDriver(options);
				}else if (BROWSER.contentEquals("internetExplorer")){
					driver = new InternetExplorerDriver();
				}else if (BROWSER.contentEquals("chrome")){
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--disable-notifications");
					System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator+"chromedriver");
					driver = new ChromeDriver(options);

				}

				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
		}
		return driver;
	}



}
