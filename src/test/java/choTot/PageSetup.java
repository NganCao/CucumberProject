package choTot;

import helpers.CommonHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import setUp.Setup;

import java.time.Duration;

public class PageSetup extends Setup {
    private WebDriver driver;
    public CommonHelper common;
    public WebDriverWait wait;
    public Actions actions;
    public SoftAssert softAssert;
    public Assert anAssert;

    public PageSetup(WebDriver driver) {
        this.driver = driver;
    }
}
