package com.core.framework.pages;

import java.io.File; 

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.sikuli.script.FindFailed;


import com.core.framework.baseClass.PropertyFileReader;

public class SelectBusSeatPage {

	protected static PropertyFileReader properties = new PropertyFileReader();
	static Logger log = Logger.getLogger(SelectBusSeatPage.class.getName());

	private static String BUS_AFTER_6 = "bus_after_6";
	private static String NON_AC_BUS = "non_ac_bus";
	private static String FILTERS = "filters";
	private static String SEATS_AVAILABLE ="seats_available";
	private static String VIEW_SEATS = "view_seats";
	private static String SELECT_BUS_ICON = "select_bus_icon";

	public void verifySelectBusSeatPage(WebDriver driver) throws Exception{

		String Url = driver.getCurrentUrl();
		Assert.assertTrue(Url.contains("https://www.redbus.in/search?fromCityName=Mumbai"));
		log.info("Verifying selected source city present in URL");
		String filters = driver.findElement(By.xpath(properties.getORProperty(FILTERS))).getText();
		Assert.assertEquals(filters, "FILTERS");

	}


	public void selectAfter6Bus(WebDriver driver) throws Exception{
		verifySelectBusSeatPage(driver);
		WebElement check6Bus = driver.findElement(By.xpath(properties.getORProperty(BUS_AFTER_6)));
		check6Bus.click();
		log.info("Bus after 6pm selected");
	}

	public void selectNonAcBus(WebDriver driver) throws Exception{

		Assert.assertTrue(driver.findElement(By.xpath(properties.getORProperty(NON_AC_BUS))).isDisplayed());
		WebElement nonACBus = driver.findElement(By.xpath(properties.getORProperty(NON_AC_BUS)));
		nonACBus.click();
		log.info("Non-AC bus selected");
	}

	public void clickSeatsAvialable(WebDriver driver) throws Exception{
		Assert.assertTrue(driver.findElement(By.xpath(properties.getORProperty(SEATS_AVAILABLE))).isDisplayed());;
		driver.findElement(By.xpath(properties.getORProperty(SEATS_AVAILABLE)));
		log.info("Clicked on Select seats");
	}

	public void select2Seats(WebDriver driver) throws Exception, FindFailed{
		clickSeatsAvialable(driver);
		driver.findElement(By.xpath(properties.getORProperty(VIEW_SEATS))).click();

		/**	WebElement busIcon = driver.findElement(By.xpath(properties.getORProperty(SELECT_BUS_ICON)));
		Actions builder = new Actions(driver);
		builder.moveToElement(busIcon).moveByOffset(135,15).click().perform(); **/
		/*
		Screen s = new Screen();
		Pattern fileInputSeatImage = new Pattern(System.getProperty("user.dir")+File.separator+"Seat.png");
		s.find(fileInputSeatImage);
		s.click(fileInputSeatImage);
		 */
	}


	public void selectBoardingDroppingPoint(WebDriver driver) throws Exception{

	}

	public void clickProceedToBook(WebDriver driver) {

	}


}

