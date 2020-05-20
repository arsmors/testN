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
    private final By PRODUCTS_lIST = By.cssSelector(".js-touch-hover");
    public String HISTORY = "recent_history/lv/";

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void openHomePage(String category) {
        baseFunc.openPage(homePage + category);
    }

    public void viewRandomProductTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            viewRandomProduct();
        }
    }

    public void viewRandomProduct() throws InterruptedException {
        Random num = new Random();
        int id = num.nextInt(5);
        getItems(id).click();
        baseFunc.openPage(homePage);
        }

    private WebElement getItems(int id) {
        List<WebElement> items = baseFunc.getElements(PRODUCTS_lIST);
        return items.get(id);
    }

    public void checkProductsDisplayedOnPage() {
        List<WebElement> listOfElements = baseFunc.getElements(PRODUCTS_lIST);
        assertFalse("ads are not displayed on page", listOfElements.isEmpty());
    }
}

//    public void viewProduct(int id) throws InterruptedException {
////        Random num = new Random();
////        int id = num.nextInt(5);
//        for (int i = 0; i < id; i++) {
//            List<WebElement> items = baseFunc.getElements(PRODUCTS_lIST);
//            for (int j = 0; j < items.size(); j++) {
//                items.get(i).click();
//                baseFunc.openPage(homePage);
//            }
//        }
//    }

//    public void viewProductTimes(int times) {
//        for (int i = 0; i < times; i++) {
//            viewProduct();
//        }



