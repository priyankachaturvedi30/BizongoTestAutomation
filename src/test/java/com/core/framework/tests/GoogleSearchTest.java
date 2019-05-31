package com.core.framework.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.core.framework.baseClass.BaseDriverSetup;
import com.core.framework.pages.GoogleHomePage;

public class GoogleSearchTest extends BaseDriverSetup {
	WebDriver driver;
	GoogleHomePage gp = new GoogleHomePage();

	@Parameters({"browser"})
	@BeforeClass
	public void beforeTest(@Optional ("chrome") String BROWSER)  throws Exception {

		driver = setup(BROWSER);
	}

	@Test
	public void launchAndSearchGoogle() throws Exception{
		try {
			driver.get("https://www.google.com");
			gp.searchGoogle(driver);
		}catch(AssertionError ex) {
			Assert.fail();
		}
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	} 


}
