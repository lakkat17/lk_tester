package PageObjects;

import Utils.WaitUtils;
import io.cucumber.java.bs.A;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;
    private Actions action;

    @FindBy(linkText = "Laptops")
    private WebElement laptopInCategoryLink;

    @FindBy(linkText = "Sony vaio i5")
    private WebElement sonyVaioi5Link;

    @FindBy(linkText = "Dell i7 8gb")
    private WebElement delli7Link;

    @FindBy(xpath = "//a[normalize-space(text())='Home']")
    private WebElement homePageLink;

    @FindBy(linkText = "Cart")
    private WebElement cartPageLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.intializeWaitObjects(driver);
        action = new Actions(driver);
    }

    public void getPage(String url) {
        driver.get(url);
    }

    public void clickOn(String element) {

        WaitUtils.untilJqueryIsDone(driver, 10);

        if (element.equals("Laptops")) {
            WaitUtils.waitUntilElementToBeClickable(laptopInCategoryLink, 1, 1);
            laptopInCategoryLink.click();
        } else if (element.equals("Sony vaio i5")) {
            WaitUtils.waitUntilElementToBeClickable(sonyVaioi5Link, 1, 1);
            sonyVaioi5Link.click();
        } else if (element.equals("Dell i7 8gb")) {
            WaitUtils.waitUntilElementToBeClickable(sonyVaioi5Link, 1, 1);
            delli7Link.click();
        } else if (element.equals("HomePage")) {
            WaitUtils.waitUntilElementToBeClickable(homePageLink, 1, 1);
            homePageLink.click();
        } else if (element.equals("Cart")) {
            WaitUtils.waitUntilElementToBeClickable(cartPageLink, 1, 1);
            cartPageLink.click();
        }
    }

}
