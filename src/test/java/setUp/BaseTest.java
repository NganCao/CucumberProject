package setUp;

import choTot.PageSetup;
import com.beust.jcommander.Parameter;
import helpers.CommonHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    private Setup setup;
    private PageSetup pageSetup;

    public WebDriver getDriver() {
        setup = new Setup();
        setup.setUpDriver();
        return setup.driver;
    }

    public void gotoHomePage() {
        setup.navigateToHome();
    }

    public CommonHelper getCommonHelper(){
        return setup.common;
    }

    public void endSession() {
        setup.tearDownDriver();
    }
}
