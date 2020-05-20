package pages;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.cucumber.java.eo.Do;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HomePage {
    BaseFunc baseFunc;

    public String homePage = "https://www.rdveikals.lv/";
    private final By PRODUCTS_lIST = By.cssSelector(".js-product");
    public String HISTORY = "recent_history/lv/";
    private final By ADD_TO_CART = By.cssSelector(".btn--280");
    public final By GROZA = By.xpath("//*[@class=\"js-buy-button-text\"]");
    public final By PRICE = By.xpath("//p[@class=\"price\"]/b");
    public final By TOTAL_PRICE = By.xpath("//*[@id=\"total_products_num_price\"]");
    private final By REMOVE = By.cssSelector(".product__controls--small > a");

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
        try {
            getItems(id).click();
        } catch (ElementClickInterceptedException e) {
            getItems(id).click();
        } catch (ElementNotInteractableException e) {
            addProductToCart();
        }
        scrollToElement();
        try {
            baseFunc.getElement(ADD_TO_CART).click();
        } catch (ElementNotInteractableException e) {
            openCategory();
            addProductToCart();
        }
    }

    public void scrollToElement() {
        JavascriptExecutor js = (JavascriptExecutor) baseFunc.driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
    }

    public void addRandomProductsToCartTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            openCategory();
            addProductToCart();
//            baseFunc.openPage(homePage);
        }
    }

    public void checkTotal() {
        List<WebElement> listOfElements = baseFunc.getElements(PRICE);
        List<String> strings = new ArrayList<String>();

        listOfElements.stream().map(WebElement::getText).forEach(strings::add);
        List<String> listNoDuplicates = Lists.newArrayList(Sets.newHashSet(strings));
        listNoDuplicates.removeAll(Arrays.asList(""));

        double[] floatArray = new double[listNoDuplicates.size()];
        for (int i = 0 ; i < listNoDuplicates.size(); i++) {
            floatArray[i] = Double.parseDouble(listNoDuplicates.get(i));
        }

        double sum = 0;
        for (int i = 0; i < floatArray.length; i++) {
            sum =  sum + floatArray[i];

        }

        String total = (baseFunc.getElement(TOTAL_PRICE)).getText();
        Double total2 = Double.parseDouble(total);

        assertEquals("total sum is correct", sum, total2, 0);
    }

    public void removeRandomProductsfromCartTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            removeProductFromCart();
        }
    }

    public void removeProductFromCart() throws InterruptedException {
        baseFunc.getElement(REMOVE).click();
    }
}



