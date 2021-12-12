package parallelscripts;

import org.testng.annotations.Test;

public class MethodTest {
	@Test
	public void searchAppiumTest() {
		long id = Thread.currentThread().getId();
		System.out.println("Search Appium Test : " + id);
	}

	@Test
	public void searchCypressTest() {
		long id = Thread.currentThread().getId();
		System.out.println("Search Cypress Test : " + id);
	}

	@Test
	public void searchJavaTest() {
		long id = Thread.currentThread().getId();
		System.out.println("Search Java Test : " + id);
	}

	@Test
	public void searchSeleneiumTest() {
		long id = Thread.currentThread().getId();
		System.out.println("Search Selenium Test : " + id);
	}

	@Test
	public void searchAPITest() {
		long id = Thread.currentThread().getId();
		System.out.println("Search API Test : " + id);
	}
}
