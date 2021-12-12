package dataparamscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataTest {

	WebDriver driver;

	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to("https://the-internet.herokuapp.com/login");
	}

	@Test

	// loginHerokuappTest function is calling readExcelData function to read data
	// from excel file
	public void loginHerokuappTest() throws IOException {

		// Call readExcelData method of the class to read data
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(readExcelData("username"));
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(readExcelData("password"));
		driver.findElement(By.xpath("//i[@class='fa fa-2x fa-sign-in']")).click();
		boolean isFlashMsg = driver.findElement(By.xpath("//div[@class='flash success']")).isDisplayed();
		Assert.assertTrue(isFlashMsg);
	}

	public String readExcelData(String colName) throws IOException {
		String colValue = "";

		// .xls--HSSF or .xlsx -XSSF
		// Prepare the path of excel file
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\testData.xlsx";

		// Create an object of FileInputStream class to read excel file
		FileInputStream fin = new FileInputStream(path);

		// Creating a workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fin);

		// Read sheet inside the workbook by its name
		XSSFSheet loginsheet = workbook.getSheet("loginData");

		// Find number of rows in excel file
		int numRows = loginsheet.getLastRowNum();
		System.out.println("No of rows :" + numRows);

		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i <= numRows; i++) {
			XSSFRow row = loginsheet.getRow(i);
			if (row.getCell(0).getStringCellValue().equalsIgnoreCase(colName)) {
				// Print Excel data in console
				colValue = row.getCell(1).getStringCellValue();
				System.out.print(colValue + "|| ");
			}
			System.out.println();
		}
		workbook.close();
		return colValue;
	}

	@AfterMethod
	public void teardown() {
		driver.close();
	}
}
