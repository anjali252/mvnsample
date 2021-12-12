package dataparamscripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataParamTest {
	WebDriver driver;

	@Parameters("browser")
	@BeforeMethod
	public void setup(String strBrowser) {
		System.out.println("Browser : " + strBrowser);
		if (strBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (strBrowser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (strBrowser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			InternetExplorerOptions options = new InternetExplorerOptions().setPageLoadStrategy(PageLoadStrategy.NONE);
			options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

			driver = new InternetExplorerDriver(options);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://the-internet.herokuapp.com/login");
	}

	// this will take data from dataprovider which we created
	@Test(dataProvider = "loginData")
	public void loginHerokuappTest(String user, String password) {
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(user);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
		boolean isFlashMsg = driver.findElement(By.xpath("//div[@class='flash success']")).isDisplayed();
		Assert.assertTrue(isFlashMsg);
	}

	// @DataProvider(name="loginData")
	// public Object[][] getData() {
	
	//Create object array with 2 rows and 2 column-first parameter is row and second is column
	// return new Object[][] { new Object[] { "tom", "Super" }, new Object[] {
	// "smith", "Secret" },
	// new Object[] { "tomsmith", "SuperSecretPassword!" } };
	// }

	// @DataProvider(name = "loginData")
	// public Object[][] getData() throws CsvValidationException, IOException {
	// String path = System.getProperty("user.dir") +
	// "\\src\\test\\resources\\loginData.csv";

	// Create an object of CSVReader
	// CSVReader reader = new CSVReader(new FileReader(path));

	//	Here col[] is an array of values from the line. 
	// String[] col;
	// ArrayList<Object[]> datalist = new ArrayList<Object[]>();

	//	while loop: It will be executed until the last line in CSV used. 
	//	For reading CSV line by line readNext() method can be used
	// while ((col = reader.readNext()) != null) {
	//	 System.out.println(col[0]);
	// Object[] record = { col[0], col[1] };
	// datalist.add(record);
	// }
	// reader.close();
	// return datalist.toArray(new Object[datalist.size()][]);
	// }

	@DataProvider(name = "loginData")
	public String[][] getData() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\loginData.json";
		FileReader reader = new FileReader(path);
		Object obj = parser.parse(reader);
		JSONObject jsonObj = (JSONObject) obj; // Read JSON file

		// JSONArray is used to parse JSON, which starts with Array brackets
		JSONArray userArray = (JSONArray) jsonObj.get("userLogins");

		System.out.println("Users List-> " + userArray); // This prints the entire json file

		String arr[][] = new String[userArray.size()][];

		// The .get method is used to access the values in the JSON by index
		for (int i = 0; i < userArray.size(); i++) {
			JSONObject user = (JSONObject) userArray.get(i);
			System.out.println("Users -> " + user);// This prints every block - one json object
			String username = (String) user.get("username");
			String password = (String) user.get("password");
			System.out.println("The username in JSON is -> " + username);
			System.out.println("The password in JSON is -> " + password);
			String[] record = new String[2];
			record[0] = username;
			record[1] = password;
			arr[i] = record;
		}
		return arr;
	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}
}
