package steps;

import io.qameta.allure.Step;
import org.testng.annotations.Test;
import pages.BaseFunc;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.HomePage;

public class MyStepdefs {
    BaseFunc baseFunc = new BaseFunc();
    HomePage homePage = new HomePage(baseFunc);
    CartPage cartPage = new CartPage(baseFunc);

    @Test
    @Step("user open the homepage")
    @When("user open the homepage")
    public void userOpenTheHomepage() {
        homePage.openHomePage("");
    }

    @Step("open {int} different products")
    @And("open {int} different products")
    public void openProductsDifferentProducts(int times) throws InterruptedException {
        homePage.viewRandomProductTimes(times);
    }

    @Step("products are visible in the history views")
    @Then("products are visible in the history views")
    public void productsAreVisibleInTheHistoryViews() {
        homePage.openHomePage("recent_history/lv/");
        homePage.checkProductsDisplayedOnPage();
        baseFunc.closePage();
    }

    @Step("add {int} different products to the cart")
    @And("add {int} different products to the cart")
    public void addProductsDifferentProductsToTheCart(int times) throws InterruptedException {
        homePage.addRandomProductsToCartTimes(times);
    }

    @Step("total sum is correct")
    @Then("total sum is correct")
    public void totalSumIsCorrect() {
        homePage.openHomePage("cart/lv/");
        cartPage.checkTotal();
    }

    @Step("user remove any {int} different products")
    @When("user remove any {int} different products")
    public void userRemoveAnyItemsDifferentProducts(int items) throws InterruptedException {
        cartPage.removeRandomProductsfromCartTimes(items);
    }

    @Step("products qty is {int} in the cart")
    @Then("products qty is {int} in the cart")
    public void productsQtyIsQtyInTheCart(int qty) {
        cartPage.qtyInCart(qty);
    }
}
