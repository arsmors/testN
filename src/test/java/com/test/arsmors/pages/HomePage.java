package com.test.arsmors.pages;

import org.openqa.selenium.*;
import java.util.*;
import static org.junit.Assert.assertFalse;

public class HomePage {
    BaseFunc baseFunc;

    public String homePage = "https://www.rdveikals.lv/";
    private final By PRODUCTS_lIST = By.cssSelector(".js-product");
    private final By ADD_TO_CART = By.cssSelector(".btn--280");
    
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
        String Mobiles = "categories/lv/388/sort/1/filter/0_0_0_0/" + "/page/" +
                (new Random().nextInt(20) + 1) + "/Mobilie-telefoni.html";
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
        getItems(id).click();
        scrollToElement();
        baseFunc.getElement(ADD_TO_CART).click();
    }

    public void scrollToElement() {
        JavascriptExecutor js = (JavascriptExecutor) baseFunc.driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
    }

    public void addRandomProductsToCartTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            openCategory();
            addProductToCart();
        }
    }
}



