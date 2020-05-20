package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BaseFunc {

    public WebDriver driver;
    private WebDriverWait wait;

    public BaseFunc() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver");
        Map<String, Object> prefs = new HashMap();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        this.driver = new ChromeDriver(options);
//        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 3);
    }

    public void openPage(String url) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    public List<WebElement> getElements(By locator) {
        Assert.assertFalse("No elements found", driver.findElements(locator).isEmpty());
        return driver.findElements(locator);
    }

    public WebElement getElement(By locator) {
        try {
//            Assert.assertFalse("Element is not found!", isElementPresent(locator));
            return driver.findElement(locator);
        } catch (WebDriverException e) {
            refresh();
            return driver.findElement(locator);
        }
//        return driver.findElement(locator);
    }

//    public void waitForElement(By locator) {
//        try {
//            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
//        } catch (TimeoutException e) {
//            return
//        }
//    }

//    public boolean isElementPresent(By locator) {
//        waitForElement(locator);
//        return getElements(locator).isEmpty();
//    }

//    public WebElement getElement(By locator) {
//        try {
//            return driver.findElement(locator);
//        } catch (NoSuchElementException e) {
//            Assert.fail("Element not found");
//            return null;
//        }

    public void refresh() {
        driver.navigate().refresh();
    }

    public void closePage() {
        driver.quit();
    }
}

