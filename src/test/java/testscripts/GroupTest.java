package testscripts;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupTest {

	// @BeforeGroups annotated method will run only once before all the test methods belonging to a
	// specified group have been executed.

	@BeforeGroups(groups = { "featureOne", "featureTwo" })
	public void setUp() {
		System.out.println("Before Each Group");

	}

	@Test(groups = { "featureOne" })
	public void searchAppiumTest() {
		System.out.println("Search Appium Test");
	}

	@Test(groups = { "featureTwo" })
	public void searchCypressTest() {
		System.out.println("Search Cypress Test");
	}

	@Test(groups = { "featureOne" })
	public void searchJavaTest() {
		System.out.println("Search Java Test");
	}

	@Test(groups = { "featureThree" })
	public void searchSeleneiumTest() {
		System.out.println("Search Selenium Test");
	}

	//	 @AfterGroups annotated method will run only once after the execution of all the test methods
	//   of a specified group.

	@AfterGroups(groups = { "featureOne", "featureTwo" })
	public void tearDown() {
		System.out.println("After Each Group");
	}
}
