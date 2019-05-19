package com.core.framework.tests;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.core.framework.baseClass.BaseDataProvider;
import com.core.framework.baseClass.BaseDriverSetup;
import com.core.framework.pages.HomePage;
import com.core.framework.pages.SearchHotelPage;

public class HotelBookingTest extends BaseDriverSetup{

	public WebDriver driver;

	static Logger log = Logger.getLogger(BusBookingTest.class.getName());

	HomePage homePage = new HomePage();
	SearchHotelPage searchHotelPage = new SearchHotelPage();

	@Parameters({"browser","url"})
	@BeforeTest
	public void setUp(@Optional ("chrome") String Browser, String url) throws Exception {
		driver = setup(Browser);
		driver.get(url);
		PropertyConfigurator.configure(System.getProperty("user.dir")+ File.separator +"config"+File.separator+"log4j.properties");

	}

	@Test(priority =1)
	public void verifyClickHotelLink() throws Exception {
		try {
			homePage.clickOnHotelLink(driver);
		}catch (AssertionError ex) {
			log.error("Cannot click on Hotel Link" , ex);
			Assert.fail();
		}
	}

	@Test(priority = 2,dataProvider="searchLocation",dataProviderClass=BaseDataProvider.class)
	public void searchandVerifyHotelByLocation(String location) throws Exception {
		try {
			searchHotelPage.enterSearchLocation(driver, location);
			searchHotelPage.clickSearchButton(driver);
			searchHotelPage.verifyHotelListLocation(driver, location);

		}catch (AssertionError ex) {
			log.error("Error in search and Verify Hotel By Location" , ex);
			Assert.fail();
		}
	}


	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
