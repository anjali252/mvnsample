package testscripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriverTest {

	public static void main(String[] args) throws MalformedURLException {

		// The remoteweddriver runs on JSON wireless protocol. Hence it becomes
		// necessary to add desired capabilities and execute the test on Chrome browser
		// and windows platform.
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		String strHub = "http://192.168.43.53:4444/wd/hub";
		WebDriver driver = new RemoteWebDriver(new URL(strHub), options);

		// Navigate to Google
		driver.navigate().to("https://www.google.co.in/");

		// Locate the searchbox using its name
		WebElement searchBox = driver.findElement(By.name("q"));

		// Enter a search query
		searchBox.sendKeys("API Tutorial");

		// Submit the query. Webdriver searches for the form using the text input
		// element automatically
		// No need to locate/find the submit button
		searchBox.sendKeys(Keys.ENTER);

		// This code will print the page title
		System.out.println("Page title is: " + driver.getTitle());
		Assert.assertEquals("API Tutorial - Google Search", driver.getTitle());
	}
/* To set up the Selenium Hub, open command prompt, and navigate to the directory where the Selenium Server Standalone jar file is stored (downloaded in Step 1).
 * java -jar  selenium-server-standalone-3.141.59.jar -role hub
 * java -Dwebdriver.chrome.driver="chromedriver.exe" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.43.53:4444/grid/register/
 *
 * */
}
