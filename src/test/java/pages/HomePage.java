package pages;

import org.openqa.selenium.*;
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
    public final By GROZA = By.xpath("//*[@class=\"js-buy-button-text\"]");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void openHomePage(String category) {
        baseFunc.openPage(homePage + category);
    }

    public void viewRandomProductTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            openCategory();
            viewRandomProduct();
            baseFunc.openPage(homePage);
        }
    }

    public void openCategory() {
        String Mobiles = "categories/lv/388/sort/1/filter/0_0_0_0/page/" +
                (new Random().nextInt(30) + 1) + "/Mobilie-telefoni.html";
        baseFunc.openPage(homePage+Mobiles);
    }

    public void viewRandomProduct() throws InterruptedException {
        Random num = new Random();
        int id = num.nextInt(5);
        try {
            getItems(id).click();
        } catch (ElementClickInterceptedException e) {
            baseFunc.openPage(homePage);
        }
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
        int id = num.nextInt(10);
        try {
            getItems(id).click();
        } catch (ElementClickInterceptedException e) {
            addProductToCart();
        } catch (ElementNotInteractableException e) {
            addProductToCart();
        }
        try {
            baseFunc.getElement(ADD_TO_CART).click();
        } catch (ElementNotInteractableException e) {
            baseFunc.openPage(homePage);
            addProductToCart();
        }
    }

    public void addRandomProductsToCartTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            openCategory();
            addProductToCart();
//            baseFunc.openPage(homePage);
        }
    }


}



