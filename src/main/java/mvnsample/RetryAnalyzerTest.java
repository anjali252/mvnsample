package mvnsample;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerTest implements IRetryAnalyzer {
	private int retryCount = 0;
	private int maxRetryCount = 3;

	/**
	 * Below method returns 'true' if the test method has to be retried else 'false'
	 * and it takes the 'Result' as parameter of the test method that just ran
	 */

	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) { // Check if test not succeed
			if (retryCount < maxRetryCount) { // Check if maxtry count is reached
				retryCount++; // Increase the maxTry count by 1
				return true; // Tells TestNG to re-run the test
			}
		}
		return false;
	}

}
