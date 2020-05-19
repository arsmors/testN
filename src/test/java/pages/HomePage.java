package pages;

public class HomePage {
    BaseFunc baseFunc;

    public String homePage = "https://www.rdveikals.lv/";

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void openHomePage(String category) {
        baseFunc.openPage(homePage + category);
    }
}
