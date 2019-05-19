package com.core.framework.pages;

import org.apache.log4j.Logger; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;

import com.core.framework.baseClass.PropertyFileReader;

public class HomePage {

	protected static PropertyFileReader properties = new PropertyFileReader();
	static Logger log = Logger.getLogger(HomePage.class.getName());

	private static String RED_BUS_LOGO = "red_bus_logo";
	private static String FROM_SOURCE = "from_source";
	private static String TO_DESTINATION = "to_destination";
	private static String SEARCH_BUSES = "search_buses";
	private static String CALENDAR_ICON ="calendar_icon";
	private static String CURRENT_DATE = "current_date";
	private static String SELECTED_OPTION = "selected_option";
	private static String HOTEL_LINK = "hotel_link";

	public void verifyLandingPage(WebDriver driver) throws Exception{

		String Url = driver.getCurrentUrl();
		Assert.assertEquals(Url, "https://www.redbus.in/");
		log.info("Verified Landing page url");
		String homeLogo = driver.findElement(By.xpath(properties.getORProperty(RED_BUS_LOGO))).getText();
		Assert.assertEquals(homeLogo, "redbus");
		log.info("Verifed Application Logo");
	}


	public void clickOnSearchBuses(WebDriver driver) throws Exception{

		selectDate(driver);
		Assert.assertTrue(driver.findElement(By.xpath(properties.getORProperty(SEARCH_BUSES))).isDisplayed());
		driver.findElement(By.xpath(properties.getORProperty(SEARCH_BUSES))).click();
		log.info("Search Buses Button clicked");
	}


	public void enterToFromCity(WebDriver driver, String source, String destination) throws Exception{
		verifyLandingPage(driver);
		driver.findElement(By.xpath(properties.getORProperty(FROM_SOURCE))).sendKeys(source);
		driver.findElement(By.xpath(properties.getORProperty(SELECTED_OPTION))).click();
		log.info("Source City Entered");
		driver.findElement(By.cssSelector(properties.getORProperty(TO_DESTINATION))).sendKeys(destination);
		driver.findElement(By.xpath(properties.getORProperty(SELECTED_OPTION))).click();
		log.info("Destination City entered");
	}

	public void selectDate(WebDriver driver) {

		driver.findElement(By.xpath(properties.getORProperty(CALENDAR_ICON))).click();
		driver.findElement(By.xpath(properties.getORProperty(CURRENT_DATE))).click();
		log.info("Current Date Selected");
	}

	public void clickOnHotelLink(WebDriver driver) throws Exception{
		verifyLandingPage(driver);
		Assert.assertTrue(driver.findElement(By.xpath(properties.getORProperty(HOTEL_LINK))).isDisplayed());
		driver.findElement(By.xpath(properties.getORProperty(HOTEL_LINK))).click();
		log.info("Hotel Link Clicked");
		String Url = driver.getCurrentUrl();
		Assert.assertEquals(Url, "https://www.redbus.in/hotels/");
		log.info("Verified Hotel Page Url");
	}

}
