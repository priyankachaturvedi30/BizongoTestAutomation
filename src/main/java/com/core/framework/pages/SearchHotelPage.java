package com.core.framework.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.core.framework.baseClass.PropertyFileReader;

public class SearchHotelPage {

	protected static PropertyFileReader properties = new PropertyFileReader();
	static Logger log = Logger.getLogger(SearchHotelPage.class.getName());


	WebDriverWait wait;
	private static String SEARCH_BOX = "search_box";
	private static String DROPDOWN_OPTION = "dropdown_option";
	private static String SEARCH_HOTEL_BUTTON = "search_hotel_button";
	private static String HOTEL_LIST = "hotel_list";
	private static String HOTEL_LIST_LOCATION = "hotel_list_location";

	public void enterSearchLocation(WebDriver driver, String location) throws Exception{
		WebElement searchLocation = driver.findElement(By.xpath(properties.getORProperty(SEARCH_BOX)));
		searchLocation.sendKeys(location);
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(properties.getORProperty(DROPDOWN_OPTION))));
		driver.findElement(By.xpath(properties.getORProperty(DROPDOWN_OPTION))).click();
		log.info("Hotel Location selected");
	}

	public void clickSearchButton(WebDriver driver) {
		driver.findElement(By.xpath(properties.getORProperty(SEARCH_HOTEL_BUTTON))).click();
		log.info("Search Hotel Button clicked");
	}

	public void verifyHotelListLocation(WebDriver driver, String location) {

		List<WebElement> hotelLocationList = null;

		List <WebElement> hotelList = driver.findElements(By.xpath(properties.getORProperty(HOTEL_LIST)));

		if(hotelList.size()!=0){
			for (WebElement ele : hotelList) {
				wait = new WebDriverWait(driver, 5);
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(properties.getORProperty(HOTEL_LIST_LOCATION))));
				hotelLocationList = ele.findElements(By.xpath(properties.getORProperty(HOTEL_LIST_LOCATION)));
				log.info("Captured Available Hotel names");
			}
			for (WebElement element : hotelLocationList) {	
				String locationName = "";
				locationName = element.getText();
				Assert.assertEquals(locationName, location);
				log.info("Verifying location for available Hotel list");
			}

		}
	}
}

