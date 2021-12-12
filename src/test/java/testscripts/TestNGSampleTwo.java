package testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import mvnsample.RetryAnalyzerTest;

public class TestNGSampleTwo {
	WebDriver driver;
	public static Properties prop;

	@BeforeTest
	public void readProperty() {
		prop = new Properties();
		String path = System.getProperty("user.dir") + "//src//test//resources//config.properties";
		try {
			FileInputStream fin = new FileInputStream(path);
			prop.load(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @Parameters("browser")
	@BeforeMethod
	//	public void setup(String strBrowser) {
	public void setup() {
		String strBrowser = prop.getProperty("browser");
		System.out.println("Browser : " + strBrowser);
		// if (strBrowser.equalsIgnoreCase("chrome")) {
		if (strBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// WebDriverManager.chromedriver().setup(); checks for the latest version of the
			// specified WebDriver binary. If the
			// binaries are not present on the machine, then it will download the WebDriver
			// binaries.

			driver = new ChromeDriver();
		} else if (strBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (strBrowser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (strBrowser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// driver.navigate().to("https://www.google.co.in/");
		driver.get(prop.getProperty("url"));
	}

	// @Test(retryAnalyzer = RetryAnalyzerTest.class)
	@Test
	public void searchAPITest() throws InterruptedException {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("API Tutorial");
		searchBox.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		Assert.assertEquals("API Tutorial - Google Search", driver.getTitle());
	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}

}
