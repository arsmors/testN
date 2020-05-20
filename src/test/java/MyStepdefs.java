import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BaseFunc;
import pages.HomePage;

public class MyStepdefs {
    BaseFunc baseFunc = new BaseFunc();
    HomePage homePage = new HomePage(baseFunc);

    @When("user open the homepage")
    public void userOpenTheHomepage() {
        homePage.openHomePage("");

    }


    @And("open {int} different products")
    public void openProductsDifferentProducts(int times) throws InterruptedException {
        homePage.viewRandomProductTimes(times);
    }

    @Then("products are visible in the history views")
    public void productsAreVisibleInTheHistoryViews() {
        homePage.openHomePage("recent_history/lv/");
        homePage.checkProductsDisplayedOnPage();


    }

    @And("add {int} different products to the cart")
    public void addProductsDifferentProductsToTheCart(int times) throws InterruptedException {
        homePage.addRandomProductsToCartTimes(times);

    }

    @Then("total sum is correct")
    public void totalSumIsCorrect() {
        homePage.openHomePage("cart/lv/");
        homePage.checkTotal();
    }


    @When("user remove any {int} different products")
    public void userRemoveAnyItemsDifferentProducts(int items) throws InterruptedException {
        homePage.removeRandomProductsfromCartTimes(items);
    }

    @Then("products qty is {int} in the cart")
    public void productsQtyIsQtyInTheCart(int qty) {
        homePage.qtyInCart(qty);
    }
}
