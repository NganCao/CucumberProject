package driverManager;

import helpers.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManagerBrowser {
    //init driver
    @Override
    public WebDriver getDriver() {
        Log.info("Launching chrome browser...");
        WebDriverManager.chromedriver().setup();
//        String path = System.getProperty("user.dir");
//        System.setProperty("webdriver.chrome.driver", path + "/src/test/java/driver/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        return new ChromeDriver();
    }
}
