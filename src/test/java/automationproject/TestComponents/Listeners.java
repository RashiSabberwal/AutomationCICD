package automationproject.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automationproject.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
    // ExtentReports setup
    private ExtentReports extent = ExtentReporterNG.getReportObject();
    
    // ThreadLocal to ensure thread-safe access to ExtentTest
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new ExtentTest for the current thread
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Set it to ThreadLocal
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log test success to the thread-local ExtentTest
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure and capture a screenshot
        ExtentTest test = extentTest.get();
        test.fail(result.getThrowable());
        
        try {
            // Get the WebDriver instance for the failed test
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        String filePath = null;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (filePath != null) {
            test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skipped tests
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // No specific implementation required for this method
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Treat timeout failures as regular failures
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // No specific implementation required for this method
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the ExtentReports object after all tests are done
        extent.flush();
    }
}
