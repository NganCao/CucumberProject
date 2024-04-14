package helpers;

import json.JsonReader;
import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonHelper {
    private WebDriver driver;
    private WebDriverWait wait;
    private Faker faker = new Faker();
    private Actions actions;
    private static JavascriptExecutor js;
    private JsonReader jsonReader;
    private Log log;

    public CommonHelper() {}

    public CommonHelper(WebDriver driver, WebDriverWait wait, Actions actions) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        System.out.println(this.driver);
        js = (JavascriptExecutor) this.driver;
    }

    //wait
    public void waitForPresent(By locator) {
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception exception) {
            System.out.println("Timeout exception: Element is not presence.");
            log.info(exception.getMessage());
        }
    }

    public void waitForPresentAllElements(By locator) {
        try{
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (Exception exception) {
            System.out.println("Timeout exception: Element is not visible.");
            log.info(exception.getMessage());
        }
    }

    public void waitForVisible(By locator) {
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception exception) {
            System.out.println("Timeout exception: Element is not visible.");
            log.info(exception.getMessage());
        }
    }

    public void waitForVisibleElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception exception) {
            System.out.println("Timeout exception: Element is not visible.");
            log.info(exception.getMessage());
        }
    }

    public void waitForUrlContains(String url) {
        try{
            wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception exception) {
            System.out.println("Timeout exception: URL does not contain with \"" + url + "\"");
            log.info(exception.getMessage());
        }
    }

    public void waitForUrlMatches(String url) {
        try{
            wait.until(ExpectedConditions.urlMatches(url));
        } catch (Exception exception) {
            System.out.println("Timeout exception: Url does not match with \"" + url + "\"");
            log.info(exception.getMessage());
        }
    }

    public void waitForText(By locator, String text) {
        try{
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (Exception exception) {
            System.out.println("Timeout exception: Element does not contain with text \"" + text + "\"");
            log.info(exception.getMessage());
        }
    }

    //get
    public WebElement getElement(By locator) {
        try {
            waitForVisible(locator);
            return driver.findElement(locator);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return null;
        }
    }

    public List<WebElement> getElementList(By locator) {
        try {
            waitForPresentAllElements(locator);
            return driver.findElements(locator);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return null;
        }
    }

    public String getText(By locator) {
        try {
            waitForVisible(locator);
            WebElement element = driver.findElement(locator);
            return element.getText();
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return "";
        }
    }

    public String getAttributeValue(By locator, String attribute) {
        try {
            waitForVisible(locator);
            WebElement element = driver.findElement(locator);
            return element.getAttribute(attribute);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return "";
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    //behaviors
    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void enterData (By locator, String data) {
        WebElement element = getElement(locator);
        if (element != null) element.sendKeys(data);
        else System.out.println("Can not get element by locator \"" + locator + "\"");
    }

    public void clickBtnByElement(WebElement element) {
        if (element != null) element.click();
        else System.out.println("Can not get element \"" + element + "\"");
    }

    public void clickBtnByLocator(By locator) {
        waitForPresent(locator);
        driver.findElement(locator).click();
//        System.out.println(element);
//        element.click();
    }

    public void clickByJs(By locator) {
        WebElement element = getElement(locator);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollByJS(By locator) {
        WebElement element = getElement(locator);
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    public void actionClick(By locator) {
        WebElement element = driver.findElement(locator);
        System.out.println(element);
        actions.moveToElement(element).click().perform();
    }

    //check
    public Boolean checkDisplayOfElement(By locator) {
        try{
            waitForVisible(locator);
            return true;
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return false;
        }
    }

    public Boolean checkDisplayOfElementByElement(WebElement element) {
        try{
            waitForVisibleElement(element);
            return true;
        } catch (Exception exception) {
            log.info(exception.getMessage());
            return false;
        }
    }

    public Boolean checkTextOfElement (By locator, String text) {
        if (getText(locator).equals(text)) return true;
        else return false;
    }

    public Boolean checkUrlContains(String url) {
        if (getCurrentUrl().contains(url)) return true;
        else return false;
    }

    public Boolean checkAttributeValue(By locator, String attribute, String value) {
        String actual = getAttributeValue(locator, attribute);
        if (actual.equals(value)) return true;
        else return false;
    }
}
