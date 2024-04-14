package setUp;

import choTot.PageSetup;
import choTot.login.stepdefinitions.LoginPage;
import helpers.CommonHelper;
import helpers.ListenerHelper;
import json.JsonReader;
import org.openqa.selenium.WebDriver;
import driverManager.DriverFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Setup {
    public static WebDriver driver;
    public static String baseUrl = null;
    private JsonReader jsonReader;
    public JsonReader.ConfigObject configObject;
    private String homeUrl = "";
    private ListenerHelper listenerHelper;
    public CommonHelper common;
    private WebDriverWait wait;
    private Actions actions;

    public void setUpDriver() {
        configObject = jsonReader.configObject();
        String browserType = jsonReader.configObject().getBrowser();
        driver = DriverFactory.initDriver(browserType);
        System.out.println("1" + driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(configObject.getWaitTime()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(configObject.getWaitTime()));
        System.out.println("setup");
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(configObject.getWaitTime()));
        listenerHelper = new ListenerHelper(driver);
        common = new CommonHelper(driver, wait, actions);
    }

    public void navigateToHome() {
        homeUrl = configObject.getUrlHome();
        System.out.println(homeUrl);
        driver.get(homeUrl);
    }

    public void tearDownDriver() {
        driver.close();
        driver.quit();
    }
}
