package webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import commonClasses.CommonOperation;

public class HomePage extends CommonOperation {

	// ---- Locators----

	By searchLocation = By.xpath("//input[@class='search-input']");
	By searchResults = By.xpath("//div[@class='content-module']/div/a");
	By actualHeading = By.xpath("//a[@class='header-city-link']/h1");
	By browseForLocation = By.xpath("//a[@class='card-button centered content-module']");
	By home = By.xpath("//a[@class='header-logo']/img");
	By weatherReport = By.xpath("//a[@class='cur-con-weather-card card-module non-ad content-module lbar-panel']");
	By expectedHeading = By.xpath("//*[@id='sub-header-recent-location']//span[@class='recent-location-display-label location-label']/h1");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public HomePage() {

	}

	public String searchCityWeather(String cityname) throws InterruptedException {
		String city = "";
		sendKeys(searchLocation, cityname);
		pressEnterKey(searchLocation);
		selectCity(searchResults, cityname);
		if(isObjectAvailable(weatherReport)) {
			return getText(actualHeading);
		}else return city ;
		
	}

	public String searchWeather(String cityname) throws InterruptedException {
		click(home);
		sendKeys(searchLocation, cityname);
		pressEnterKey(searchLocation);
		return getText(expectedHeading);
	}

}
