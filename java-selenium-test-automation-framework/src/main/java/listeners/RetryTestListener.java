package listeners;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class RetryTestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult testResult) {
        Reporter.setCurrentTestResult(testResult);
        if (testResult.getMethod().getRetryAnalyzer(testResult).retry(testResult)) {
            testResult.setStatus(ITestResult.SKIP);
        }

        Reporter.setCurrentTestResult(null);
    }
}
