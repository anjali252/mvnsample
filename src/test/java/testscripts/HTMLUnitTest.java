package testscripts;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.Test;

public class HTMLUnitTest {

	WebDriver driver;

	@Test
	public void f() {

		// Creating a new instance of the HTML unit driver
		driver = new HtmlUnitDriver();
		
		// Navigate to Google	
		driver.navigate().to("https://www.google.co.in/");

		// Locate the searchbox using its name
		WebElement searchBox = driver.findElement(By.name("q"));

		// Enter a search query	
		searchBox.sendKeys("API Tutorial");

		// Submit the query. Webdriver searches for the form using the text input element automatically		
		// No need to locate/find the submit button
		searchBox.sendKeys(Keys.ENTER);

		// This code will print the page title		
		System.out.println("Page title is: " + driver.getTitle());	
		Assert.assertEquals("API Tutorial - Google Search", driver.getTitle());
	}
}
