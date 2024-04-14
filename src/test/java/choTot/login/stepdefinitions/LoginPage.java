package choTot.login.stepdefinitions;

import choTot.PageSetup;
import helpers.CommonHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import setUp.BaseTest;
import setUp.Setup;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginPage extends BaseTest {
    private WebDriver driver;
    private CommonHelper helper;
    public String actualRS = "";
    private By accountButton = By.id("btnundefinedundefined");
    private By LoginRegisterButton = By.xpath("//div[@class=\"aw__ntc1674\"]/a/span[2]");
    private By logo = By.xpath("//div[@class=\"logo\"]/img");
    private By title = By.xpath("//div[@class=\"logo\"]/following-sibling::h3");
    private By phoneField = By.name("phone");
    private By phoneLabel = By.xpath("//input[@name=\"phone\"]/following-sibling::label");
    private By passwordField = By.name("password");
    private By passwordLabel = By.xpath("//input[@name=\"password\"]/following-sibling::label");
    private By forgotButton = By.xpath("//a[contains(@class,\"forgot-password-btn\")]");
    private By loginButton = By.xpath("//button[contains(@class,\"login-btn\")]");
    private By facebook = By.xpath("//button[contains(text(),\"Facebook\")]/*[name()=\"svg\"]");
    private By facebookButton = By.xpath("//button[contains(text(),\"Facebook\")]");
    private By google = By.xpath("//button[contains(text(),\"Google\")]/*[name()=\"svg\"]");
    private By googleButton = By.xpath("//button[contains(text(),\"Google\")]");
    private By googleLoginButton = By.id("google-login-btn");
    private By appleId = By.xpath("//button[contains(text(),\"Apple ID\")]/*[name()=\"svg\"]");
    private By appleIdButton = By.xpath("//button[contains(text(),\"Apple ID\")]");
    private By registerLabel = By.xpath("//p[contains(@class,\"rnv45ui\")]");
    private By registerButton = By.xpath("//p[contains(@class,\"rnv45ui\")]/a");
    //message
    private By phoneRequiredMsg = By.xpath("//input[@name=\"phone\"]/parent::div/following-sibling::p");
    private By passwordRequiredMsg = By.xpath("//input[@name=\"password\"]/parent::div/following-sibling::p");
    private By wongPassMsg = By.xpath("//div[contains(@class,\"no-button\") and contains(@class,\"error\")]");
    private By createPostToolTip = By.xpath("//div[contains(@class,\"aw__cosl5o3\")]");
    private By closeCreateTooltip = By.xpath("//div[contains(@class,\"aw__c10qu4al\")]/*[name()=\"svg\"]");
    private By loginNewDeviceDialog = By.xpath("//div[contains(@class,\"seu65h8\")]");
    public WebDriverWait wait;
    public Actions actions;
    private PageSetup pageSetup;

    public LoginPage() {
        driver = getDriver();
        helper = getCommonHelper();
    }

    @Before
    public void initHome() {
        gotoHomePage();
    }

    @After
    public void tearDown() {
        endSession();
    }

    //behaviors
    @Given("I go to Login page")
    public void gotoLoginPage() {
        helper.actionClick(closeCreateTooltip);
        helper.clickBtnByLocator(accountButton);
        helper.clickBtnByLocator(LoginRegisterButton);
        checkDisplayOfLogo();
    }

    @When("I enter phone {string} and password {string}")
    public void enterPhoneAndPassword(String phone, String password) {
        helper.enterData(phoneField, phone);
        helper.enterData(passwordField, password);
        helper.clickBtnByLocator(loginButton);
    }

    @When("I click login button")
    public void clickLoginButton() {
        helper.clickBtnByLocator(loginButton);
    }

    //check
    @Then("^I should see logo$")
    public void checkDisplayOfLogo() {
        Assert.assertTrue(helper.checkDisplayOfElement(logo));
    }

    @Then("^I should see title$")
    public void checkDisplayOfTitle() {
        Assert.assertTrue(helper.checkDisplayOfElement(title));
    }

    @Then("^I should see phone field$")
    public void checkDisplayOfPhoneField() {
        Assert.assertTrue(helper.checkDisplayOfElement(phoneField));
    }

    @Then("^I should see password field$")
    public void checkDisplayOfPasswordField() {
        Assert.assertTrue(helper.checkDisplayOfElement(passwordField));
    }

    public void checkDisplayOfForgotPassword() {
        Assert.assertTrue(helper.checkDisplayOfElement(forgotButton));
    }

    public void checkDisplayOfLoginButton() {
        Assert.assertTrue(helper.checkDisplayOfElement(loginButton));
    }

    public void checkDisplayOfFacebookIcon() {
        Assert.assertTrue(helper.checkDisplayOfElement(facebook));
    }

    public void checkDisplayOfGoogleIcon() {
        Assert.assertTrue(helper.checkDisplayOfElement(google));
    }

    public void checkDisplayOfAppleIcon() {
        Assert.assertTrue(helper.checkDisplayOfElement(appleId));
    }

    public void checkDisplayOfFacebook() {
        Assert.assertTrue(helper.checkDisplayOfElement(facebookButton));
    }

    public void checkDisplayOfGoogle() {
        Assert.assertTrue(helper.checkDisplayOfElement(googleButton));
    }

    public void checkDisplayOfAppleId() {
        Assert.assertTrue(helper.checkDisplayOfElement(appleIdButton));
    }

    public void checkTextOfForgotButton() {
        Assert.assertTrue(helper.checkDisplayOfElement(facebookButton));
    }

    @Then("^I should see the required phone message$")
    public void checkDisplayOfRequiredPhone() {
        Assert.assertTrue(helper.checkDisplayOfElement(phoneRequiredMsg), "Required phone did not display");
    }

    @Then("^I should see the required password message$")
    public void checkDisplayOfRequiredPassword() {
        Assert.assertTrue(helper.checkDisplayOfElement(passwordRequiredMsg), "Required password did not display");
    }

    @Then("I should see the wrong message")
    public void iShouldSeeTheWrongPhonePasswordMessage() {
        Assert.assertTrue(helper.checkDisplayOfElement(wongPassMsg), "Wrong phone or password did not display");
    }

    @Then("I should see a get Otp message")
    public void iShouldSeeAGetOtpMessage() {
        Assert.assertTrue(helper.checkDisplayOfElement(loginNewDeviceDialog), "Get OTP on new device dialog did not display");
    }

    @Then("I should see text {string} in phone label")
    public void iShouldSeeTextInPhone(String expectedText) {
        Assert.assertTrue(helper.checkTextOfElement(phoneLabel, expectedText), "Actual: " + helper.getText(phoneLabel));
    }

    @Then("I should see text {string} in password label")
    public void iShouldSeeTextInPassword(String expectedText) {
        Assert.assertTrue(helper.checkTextOfElement(passwordLabel, expectedText), "Actual: " + helper.getText(phoneLabel));
    }
}
