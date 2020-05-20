package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertFalse;

public class HomePage {
    BaseFunc baseFunc;

    public String homePage = "https://www.rdveikals.lv/";
    private final By PRODUCTS_lIST = By.cssSelector(".js-product");
    public String HISTORY = "recent_history/lv/";
    private final By ADD_TO_CART = By.cssSelector(".btn--280");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void openHomePage(String category) {
        baseFunc.openPage(homePage + category);
    }

    public void viewRandomProductTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            viewRandomProduct();
            baseFunc.openPage(homePage);
        }
    }

    public void viewRandomProduct() throws InterruptedException {
        Random num = new Random();
        int id = num.nextInt(5);
        getItems(id).click();
        }

    private WebElement getItems(int id) {
        List<WebElement> items = baseFunc.getElements(PRODUCTS_lIST);
        return items.get(id);
    }

    public void checkProductsDisplayedOnPage() {
        List<WebElement> listOfElements = baseFunc.getElements(PRODUCTS_lIST);
        assertFalse("ads are not displayed on page", listOfElements.isEmpty());
    }

    public void addProductToCart() throws InterruptedException {
        Random num = new Random();
        int id = num.nextInt(5);
        getItems(id).click();
        baseFunc.getElement(ADD_TO_CART).click();
    }

    public void addRandomProductsToCartTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            addProductToCart();
            baseFunc.openPage(homePage);
        }
    }
}



