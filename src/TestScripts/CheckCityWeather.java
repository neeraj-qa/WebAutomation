package TestScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import webPages.HomePage;

public class CheckCityWeather extends HomePage {

	@Test (priority = 1, enabled = true)
	public void searchWeatherOfCorrectCity() throws InterruptedException {
		System.out.println("----Positive test case, searching weather for Jaipur city----");
		String expectedCity = "Jaipur, Rajasthan";		
		String actualCity = homePage.searchCityWeather("Jaipur");
		Assert.assertEquals(actualCity, expectedCity);		
	}
	
	@Test (priority = 2, enabled = false)
	public void searchWeatherOfWrongCity() throws InterruptedException {
		System.out.println("-----Negative test case, entering special characters in search bar----");
		String expected = "BROWSE FOR A LOCATION";
		String actual = homePage.searchWeather("........");
		Assert.assertEquals(actual, expected);
	}
}
