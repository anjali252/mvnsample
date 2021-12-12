package parallelscripts;

import org.testng.annotations.Test;

public class MethodInvokeTest {

	// threadPoolSize: The number of threads we would like to create and run the test parallelly.

	// invocationCount: The number of times we would like to invoke this method.

	// timeOut: The maximum time a test execution should take. If exceeded, the test fails 
	// automatically.

	@Test(threadPoolSize = 4, invocationCount = 8, timeOut = 1000)
	public void test() {
		long id = Thread.currentThread().getId();
		System.out.println("Test in MethodInvokeTest : " + id);
	}
}
