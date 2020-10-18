package PageObjects;

import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

public class CartPage {
    private WebDriver driver;
    private Logger logger;

    @FindBy(xpath = "//td[normalize-space(text())='Dell i7 8gb']/following-sibling::td/a[text()='Delete']")
    private WebElement delli7DeleteLink;

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderBtn;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseBtn;

    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okBtn;

    @FindBy(id = "name")
    private WebElement nameTxtBox;

    @FindBy(id = "country")
    private WebElement countryTxtBox;

    @FindBy(id = "city")
    private WebElement cityTxtBox;

    @FindBy(id = "card")
    private WebElement cardTxtBox;

    @FindBy(id = "month")
    private WebElement monthTxtBox;

    @FindBy(id = "year")
    private WebElement yearTxtBox;

    @FindBy(xpath = "//*[contains(@class,'sweet-alert')]//p")
    private WebElement purchaseInfo;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WaitUtils.intializeWaitObjects(driver);
        logger = Logger.getLogger(CartPage.class.getName());
    }

    public void clickOn(String element) {

        WaitUtils.untilJqueryIsDone(driver, 10);

        if (element.equals("Dell i7 8gb")) {
            WaitUtils.waitUntilElementToBeClickable(delli7DeleteLink, 1, 1);
            delli7DeleteLink.click();
        } else if (element.equals("Place Order")) {
            WaitUtils.waitUntilElementToBeClickable(placeOrderBtn, 1, 1);
            placeOrderBtn.click();
        } else if (element.equals("Purchase")) {
            WaitUtils.waitUntilElementToBeClickable(purchaseBtn, 1, 1);
            purchaseBtn.click();
        }
        else if (element.equals("OK")) {
            WaitUtils.waitUntilElementToBeClickable(okBtn, 1, 1);
            okBtn.click();
        }
    }

    public void setName(String name) {
        nameTxtBox.sendKeys(name);
    }

    public void setCountry(String country) {
        countryTxtBox.sendKeys(country);
    }

    public void setCity(String city) {
        cityTxtBox.sendKeys(city);
    }

    public void setCard(String card) {
        cardTxtBox.sendKeys(card);
    }

    public void setMonth(String month) {
        monthTxtBox.sendKeys(month);
    }

    public void setYear(String year) {
        yearTxtBox.sendKeys(year);
    }

    public void clickPurchaseBtn() {
        purchaseBtn.click();
    }

    public void setFormFields(List<String> fields) {
        WaitUtils.waitUntilElementToBeClickable(nameTxtBox, 1, 1);
        nameTxtBox.sendKeys(fields.get(0));
        countryTxtBox.sendKeys(fields.get(1));
        cityTxtBox.sendKeys(fields.get(2));
        cardTxtBox.sendKeys(fields.get(3));
        monthTxtBox.sendKeys(fields.get(4));
        yearTxtBox.sendKeys(fields.get(5));
        logger.info("All form fields have been filled.");
    }

    public String[] logPurchaseInfo() {
        WaitUtils.waitUntilElementToBeVisible(purchaseInfo, 1, 1);
        String orderInfo = purchaseInfo.getText();
        String[] orderDetails = orderInfo.split("\n");

        logger.info("logging Purchase id and Purchase amount...");
        logger.info("Purchase " + orderDetails[0] + " And " + orderDetails[1]);

        return orderDetails;
    }

}
