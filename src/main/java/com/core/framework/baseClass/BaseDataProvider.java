package com.core.framework.baseClass;
import org.testng.annotations.DataProvider;


public class BaseDataProvider{

	@DataProvider(name="searchCity")
	public static Object[][] getCityData(){
		return new Object[][] {
			{"Mumbai" , "Nasik"		}
		};
	}

	@DataProvider(name="searchLocation")
	public static Object[][] getLocationData(){
		return new Object[][] {
			{"Andheri, Mumbai"		}
		};
	}
}
