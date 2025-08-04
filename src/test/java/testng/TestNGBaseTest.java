package testng;

import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.ReadAPIs;
import utilities.ReportLog;

public abstract class TestNGBaseTest {

    @BeforeSuite
    public static void setupBaseUri() {
        RestAssured.baseURI = ReadAPIs.getBaseUri();
        ReportLog.log("Base URI set to: " + RestAssured.baseURI);
    }

    @BeforeMethod
    public void beforeEachTestMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ReportLog.log(">>> STARTING TEST: " + testName);
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ReportLog.log("<<< FINISHED TEST: " + testName);
    }

    // Optional utility to use in your own test methods
    protected void logTestStart(String testName) {
        ReportLog.log(">>> START: " + testName);
    }

    protected void logTestEnd(String testName) {
        ReportLog.log("<<< END: " + testName);
    }
}
