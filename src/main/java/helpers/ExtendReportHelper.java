package helpers;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ExtendReportHelper {
    private static String exportFilePath = "./extent-reports/extent-report.html";
    public static final ExtentReports extendReports = new ExtentReports();
    public static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(exportFilePath);
        reporter.config().setDocumentTitle("Report");
        reporter.config().setReportName("choTot");
        extendReports.attachReporter(reporter);
        return extendReports;
    }

    public static ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest saveToReport(String scenarioName, String testName, String desc) {
        ExtentReports extentReports = getExtentReports();
        ExtentTest extentTest = extentReports.createTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), extentTest);
        return extentTest;
    }

    public static void addScreenShot(WebDriver driver, Status status, String message) {
        String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        getTest().log(status, message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
    }

    public static void logMessage(Status status, String message) {
        getTest().log(status, message);
    }
}
