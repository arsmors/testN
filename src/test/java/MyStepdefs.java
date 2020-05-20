import io.cucumber.java.en.And;
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

}
