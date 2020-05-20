package pages;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeMath.round;
import static org.junit.Assert.assertEquals;

public class CartPage {
    BaseFunc baseFunc;

    public final By PRICE = By.xpath("//p[@class=\"price\"]/b");
    public final By TOTAL_PRICE = By.xpath("//*[@id=\"total_products_num_price\"]");
    private final By REMOVE = By.cssSelector(".product__controls--small > a");
    private final By CART_PRODUCTS = By.cssSelector(".cart-product-list > .product");

    public CartPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
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

        assertEquals("total sum is incorrect", round(sum, 2), round(total2, 2), 0);
    }

    public void removeRandomProductsfromCartTimes(int times) throws InterruptedException {
        for (int i = 0; i < times; i++) {
            removeProductFromCart();
        }
    }

    public void removeProductFromCart() throws InterruptedException {
        baseFunc.getElement(REMOVE).click();
    }

    public void qtyInCart(int qty) {
        List<WebElement> listOfElements = baseFunc.getElements(CART_PRODUCTS);
        assertEquals("total qty of products in cart is incorrect", listOfElements.size(), qty, 0);
    }
}
