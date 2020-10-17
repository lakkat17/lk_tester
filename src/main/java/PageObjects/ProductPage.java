package PageObjects;

import Utils.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

    private WebDriver driver;

    @FindBy(linkText = "Add to cart")
    private WebElement addToCartBtn;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.intializeWaitObjects(driver);
    }

    public Alert alert() {
        WaitUtils.waitUntilAlertToBeVisible(1, 1);
        return driver.switchTo().alert();
    }

    public void clickOn(String element) {
        WaitUtils.untilJqueryIsDone(driver,10);

        WaitUtils.waitUntilElementToBeClickable(addToCartBtn, 1, 1);
        addToCartBtn.click();
    }

}
