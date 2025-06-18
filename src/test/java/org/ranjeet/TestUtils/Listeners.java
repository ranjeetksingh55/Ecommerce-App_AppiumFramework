

package org.ranjeet.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AppiumUtils;

public class Listeners extends AppiumUtils implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExportReporterNG.getReporterObject();

    // ThreadLocal to ensure that ExtentTest instances are thread-safe in parallel execution
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        // Start a new test in the report for each test case
        test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test); // Associate the test with the current thread
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log the failure and the reason for the failure
        extentTest.get().log(Status.FAIL, result.getThrowable());
        try {
            extentTest.get().addScreenCaptureFromPath("screenshot.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // You can add screenshot capture logic here if needed
        // For example:
        // String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
        // extentTest.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        extentTest.get().log(Status.FAIL, "Test Failed but within success percentage");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush the ExtentReports to write the report at the end of all tests
        extent.flush();
    }
}

