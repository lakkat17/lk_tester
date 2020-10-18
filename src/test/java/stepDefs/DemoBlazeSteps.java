package stepDefs;

import PageObjects.CartPage;
import PageObjects.HomePage;

import PageObjects.ProductPage;
import io.cucumber.datatable.DataTable;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import managers.BrowserManager;
import managers.POManager;
import managers.WebDriverManager;
import org.openqa.selenium.WebDriver;

public class DemoBlazeSteps {

    POManager poManager;
    WebDriverManager webDriverManager;
    BrowserManager browserManager;
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    String[] orderDetails;

    @Before("@web")
    public void initialize() {
        //loading drivers
        webDriverManager = new WebDriverManager();
        driver = webDriverManager.getDriver();

        browserManager = new BrowserManager(driver);
        browserManager.setIfWindowMax();

        poManager = new POManager(driver);
        homePage = poManager.getHomePage();

      /*  shut down driver when the test suite exits.
         This shutdown hook will be run by JVM at shutdown time*/
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                webDriverManager.closeDriver()));
    }

    @Given("^user is on Home Page$")
    public void navigate_to_HomePage() {
        homePage.getPage(browserManager.getAppUrl());
    }

    @When("^user navigates to (\\w+)(?: under categories)?(?: Page)?$")
    public void click_On(String element) {
        homePage.clickOn(element);
    }

    @And("^user navigates to (.*) product$")
    public void click_On_Home(String element) {
        homePage.clickOn(element);
    }

    @Then("^user clicks on (.*) on product page$")
    public void click_On_Product(String element) {
        productPage = poManager.getProductPage();
        productPage.clickOn(element);
        productPage.alert().accept();
    }

    @Then("^user clicks on (.*) (?:delete link|button)?$")
    public void click_On_Cart(String element) {
        cartPage = poManager.getCartPage();
        cartPage.clickOn(element);
    }

    @And("^fill all the form fields$")
    public void fillDetails(DataTable dataTable) {
        cartPage.setFormFields(dataTable.asList());
    }

    @And("^user get purchase id and amount$")
    public void getOrderDetails() {
        orderDetails = cartPage.logPurchaseInfo();
    }

    @Then("^verify (\\d+) as purchase amount$")
    public void verifyPurchaseAmount(int amt) {
        assert Integer.parseInt(orderDetails[1].split(" ")[1]) == amt;

    }

}
