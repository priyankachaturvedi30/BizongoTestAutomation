package com.core.framework.tests;

import org.testng.annotations.Test; 
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.core.framework.baseClass.BaseDataProvider;
import com.core.framework.baseClass.BaseDriverSetup;
import com.core.framework.pages.HomePage;
import com.core.framework.pages.SelectBusSeatPage;

public class BusBookingTest extends BaseDriverSetup{

	public WebDriver driver;

	static Logger log = Logger.getLogger(BusBookingTest.class.getName());

	HomePage homePage = new HomePage();
	SelectBusSeatPage selectbusSeatPage = new SelectBusSeatPage();

	@Parameters({"browser", "url"})
	@BeforeTest
	public void setUp(@Optional ("chrome") String Browser, String url) throws Exception {
		driver = setup(Browser);
		driver.get(url);
		PropertyConfigurator.configure(System.getProperty("user.dir")+ File.separator +"config"+File.separator+"log4j.properties");

	}

	@Test(priority = 1,dataProvider="searchCity",dataProviderClass=BaseDataProvider.class)
	public void searchBus(String sourceCity, String destinationCity) throws Exception{
		try {
			homePage.enterToFromCity(driver, sourceCity, destinationCity);
			homePage.clickOnSearchBuses(driver);
		}catch (AssertionError ex) {
			log.error("Search Buses Verification failed" , ex);
			Assert.fail();
		}
	}

	@Test(priority= 2)
	public void advanceSearch() throws Exception{
		try {
			selectbusSeatPage.selectAfter6Bus(driver);
			selectbusSeatPage.selectNonAcBus(driver);

		}catch(AssertionError ex) {
			log.error("Advance Bus Search failed", ex);
			Assert.fail();
		}
	}

	@Test(priority = 3)
	public void selectSeatAndBook() throws Exception{
		try {
			selectbusSeatPage.select2Seats(driver);
			//	selectbusSeatPage.selectBoardingDroppingPoint(driver);
			//	selectbusSeatPage.clickProceedToBook(driver);
		}catch(AssertionError ex) {
			log.error("Seat and booking selection failed", ex);
			Assert.fail();
		}
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	} 
}
