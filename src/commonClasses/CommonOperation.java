package commonClasses;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import webPages.HomePage;


public class CommonOperation {
	
	WebElement element;
	JavascriptExecutor jse;
	Select select;
	public WebDriver driver;
	protected HomePage homePage;
	
/*----Common methods----*/	
	
	@Parameters("browser")
	@BeforeClass
	public void setEnvironment(String browser) {
		String url = "https://www.accuweather.com/";
		
		if (browser.equalsIgnoreCase("chrome")) {
			String getProperties = System.getProperty("user.dir");
			String chromePath = getProperties + "\\Drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromePath);

			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("disable-extensions");
			opt.addArguments("--start-maximized");
			opt.addArguments("disable-infobars");
			driver = new ChromeDriver(opt);
			System.out.println("Running Chrome");
		} else {
			String getProperties = System.getProperty("user.dir");
			String geckoPath = getProperties + "\\Drivers\\geckodriver.exe";
			System.setProperty("webdriver.chrome.driver", geckoPath);
			driver = new FirefoxDriver();
			System.out.println("Running FireFox");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
			
		
		driver.get(url);
		
		homePage =  new HomePage(driver);
		driver.manage().deleteAllCookies();
		
	}

	public CommonOperation(WebDriver driver) {
		this.driver = driver;
	}
	
	public CommonOperation() {}

	public void click(By by) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));		
		element = driver.findElement(by);
		element.click();		
	}
	
	public void sendKeys(By by, String data) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		element = driver.findElement(by);
		element.clear();
		element.sendKeys(data.trim());		
	}
	
	public void pressEnterKey(By by) {
		element = driver.findElement(by);				
		element.sendKeys(Keys.ENTER);
	}
	
	public void selectCity(By by, String cityname) {
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		List<WebElement> cityList = driver.findElements(by);		
		  for (WebElement element : cityList) {
			  String city = element.getText();
				if (city.contains(cityname)) {
					element.click();
					System.out.println(city+ " is selected");
					break;
				}
		  }
	}
	
	public String getText(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		element = driver.findElement(by);
		return element.getText();		
	}
	
	public boolean isObjectAvailable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		element = driver.findElement(by);
		if(element.isDisplayed()) {
			return true;
		}else
			return false;
	}
	
	
	@AfterSuite
	public void quitDriver() {
		driver.quit();
	}
}
