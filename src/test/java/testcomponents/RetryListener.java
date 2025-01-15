package testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

    int count = 0;
    int maxRetry = 1; //Initial count should be 0 and maxRetry refers no of retry
    @Override
    public boolean retry(ITestResult iTestResult) {

        if(count<maxRetry) //retry the test until it return false
        {
            count++;
            return true;
        }
        return false;
    }
}
