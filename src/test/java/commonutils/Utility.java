package commonutils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {

	public static String getScreenshotPath(WebDriver driver) {

		//		getScreenShotAs() method returns a file object to be stored onto a file variable. Casting the web driver instance to Take 
		//		Screenshot is necessary to use the method.

		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);

		//		This statement creates a folder named ‘screenshots’ within the ‘project’ folder and stores the file name as the current 
		//		system time.

		String srcFile = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File dest = new File(srcFile);
		try {

			//			These statements copy all error images to the destination folder.

			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return srcFile;
	}
}
