package driverManager;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    public static Map<String, DriverManagerBrowser> driverManagerBrowserMap;

    static {
        driverManagerBrowserMap = new HashMap<>();
        driverManagerBrowserMap.put("chrome", new ChromeDriverManager());
    }

    public static WebDriver initDriver(String browser) {
        DriverManagerBrowser managerBrowser = driverManagerBrowserMap.get(browser.toLowerCase());
        return managerBrowser.getDriver();
    }
}
