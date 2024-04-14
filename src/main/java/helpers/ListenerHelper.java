package helpers;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class ListenerHelper implements ITestListener {
    private ExtendReportHelper extendReportHelper;
    private WebDriver driver;

    public ListenerHelper() {}

    public ListenerHelper(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void onFinish(ITestContext arg0) {
        // TODO Auto-generated method stub
        extendReportHelper.getExtentReports().flush();
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub
        Log.info("Start test for " + context.getName() + " - " + context.getStartDate());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        Log.error("Test failed but it is in defined success ratio " + result.getName());
        extendReportHelper.logMessage(Status.FAIL, "Test failed but it is in defined success ratio " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        Log.error("Failed: " + result.getName());
        extendReportHelper.logMessage(Status.FAIL, result.getMethod().getDescription());
        extendReportHelper.logMessage(Status.FAIL, result.getThrowable().toString());
        extendReportHelper.logMessage(Status.FAIL, "Failed: " + result.getName());
        extendReportHelper.logMessage(Status.FAIL, "Test name: " + result.getName() + " - " + result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
        if (!result.getMethod().getDescription().equals(""))
            extendReportHelper.logMessage(Status.FAIL, "Description: " + result.getMethod().getDescription());
        extendReportHelper.logMessage(Status.FAIL, "Failure case name: " + result.getName());
        extendReportHelper.logMessage(Status.FAIL, "Failure Message: : " + result.getThrowable().getMessage());
//        extendReportHelper.addScreenShot(driver, Status.FAIL, result.getTestName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        Log.warn(result.getName() + " test is skipped.");
        extendReportHelper.logMessage(Status.SKIP, result.getName() + " test is skipped.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        Log.info("Scenario\n" + result.getName() + ": " + result.getMethod().getDescription());
        extendReportHelper.saveToReport(result.getInstanceName(), result.getMethod().getMethodName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        Log.info(result.getInstanceName() + " " + result.getMethod().getMethodName() + " is PASSED!");
        extendReportHelper.logMessage(Status.PASS, "Test name: " + result.getMethod().getMethodName() + " - " + result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).testName());
        if (!result.getMethod().getDescription().equals(""))
            extendReportHelper.logMessage(Status.PASS, "Description: " + result.getMethod().getDescription());
//        extendReportHelper.addScreenShot(driver, Status.PASS, result.getTestName());
    }
}
