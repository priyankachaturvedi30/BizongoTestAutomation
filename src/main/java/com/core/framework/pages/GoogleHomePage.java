package com.core.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.core.framework.baseClass.PropertyFileReader;

public class GoogleHomePage {

	private static String SEARCH_BOX = "search_box";
	private static String SUBMIT_BUTTON = "submit_button";

	protected static PropertyFileReader properties = new PropertyFileReader();

	public void searchGoogle(WebDriver driver) throws Exception {
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com/");
		WebElement abc = driver.findElement(By.name(properties.getORProperty(SEARCH_BOX)));
		abc.sendKeys("Bizongo");

		driver.findElement(By.xpath(properties.getORProperty(SUBMIT_BUTTON)));

	}
}
