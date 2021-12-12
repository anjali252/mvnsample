package testscripts;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import commonutils.Utility;

public class TestNGSample {
	WebDriver driver;
	ExtentTest extentTests;
	ExtentReports reports;
	ExtentHtmlReporter htmlReports;

	@BeforeTest
	public void setExtent() {
		reports = new ExtentReports();

		//		ExtentReports class generates HTML reports based on a path specified by the tester.

		//		htmlReports = new ExtentHtmlReporter("E:\\Libs_Workshop\\Screenshot\\ExtentReport.html");
		htmlReports = new ExtentHtmlReporter(System.getProperty("user.dir") + "//reports//ExtentReport.html");
		reports.attachReporter(htmlReports);
	}

	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "E:\\Libs_Workshop\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://www.google.co.in/");
	}

	// If we don’t mention any priority, testng will execute the @Test methods based
	// on alphabetical
	// order of their method names irrespective of their place of implementation in
	// the code.

	@Test
	public void searchJavaTest() {
		extentTests = reports.createTest("Search Java Test");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.sendKeys(Keys.ENTER);
		Assert.assertEquals("Java Tutorial - Google Search", driver.getTitle());
	}

	// Sometimes, it happens that our code is not ready and the test case written to
	// test that
	// method/code fails. In such cases, annotation @Test(enabled = false) helps to
	// disable this
	// test case

	@Test(enabled = false)
	// @Test
	public void searchSeleniumTest() {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.sendKeys(Keys.ENTER);
		Assert.assertEquals("Selenium Tutorial - Google Search", driver.getTitle());
	}

	// dependsOnMethods lets us make a test depend on a particular method.

	// @Test(alwaysRun = true, dependsOnMethods = "searchCypressTest")

	// If alwaysRun set to true, this configuration method will be run even if one
	// or more methods
	// invoked previously failed or was skipped.

	public void searchAppiumTest() {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Appium Tutorial");
		searchBox.sendKeys(Keys.ENTER);
		Assert.assertEquals("Appium Tutorial - Google Search", driver.getTitle());
	}

	// we have assigned the Priority to each test case means test case will the
	// lower priority value
	// will be executed first.

	@Test(priority = 1)
	public void searchCypressTest() {
		extentTests = reports.createTest("Search Cypress Test");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Cypress Tutorial");
		searchBox.sendKeys(Keys.ENTER);
		Assert.assertEquals("Cypressing Tutorial - Google Search", driver.getTitle());
	}

	@AfterTest
	public void finishReports() {

		//		Erases any previous data on a relevant report and creates a whole new report.
		//		Don’t forget to use the flush() method, since the report will not be generated otherwise.

		reports.flush();
	}

	//	Capture screenshots only when a test fails, since they consume a lot of memory.

	@AfterMethod
	public void teardown(ITestResult result) throws IOException {

		if (ITestResult.FAILURE == result.getStatus()) {
			String path = Utility.getScreenshotPath(driver);
			extentTests.fail(result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		driver.close();
	}
}
