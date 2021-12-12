package testscripts;

import org.testng.annotations.Test;

public class GroupTestTwo {
	@Test(groups= {"featureOne"})
	public void searchAppiumTest() {
		System.out.println("Search Appium Test");
	}
}
