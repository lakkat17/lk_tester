package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WaitUtils {
    private static WebDriver driver;

    public static void intializeWaitObjects(WebDriver driver) {
        WaitUtils.driver = driver;
    }

    public static void waitUntilElementToBeClickable(WebElement element, int timOutInMins, int pollTimeInSecs) {
        new FluentWait<>(driver)
                .withTimeout(timOutInMins, TimeUnit.MINUTES)
                .pollingEvery(pollTimeInSecs, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitUntilElementToBeVisible(WebElement element, int timOutInMins, int pollTimeInSecs) {
        new FluentWait<>(driver)
                .withTimeout(timOutInMins, TimeUnit.MINUTES)
                .pollingEvery(pollTimeInSecs, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    // waiting for background ajax call to complete
    public static void untilJqueryIsDone(WebDriver driver, int timeoutInSecs) {
        until(driver, (d) ->
        {
            Boolean isJqueryDone = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            return isJqueryDone;
        }, timeoutInSecs);
    }

    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, int timeoutInSecs) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSecs);
        webDriverWait.withTimeout(timeoutInSecs, TimeUnit.SECONDS);
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void waitUntilAlertToBeVisible(int timOutInMins, int pollTimeInSecs) {
        new FluentWait<>(driver)
                .withTimeout(timOutInMins, TimeUnit.MINUTES)
                .pollingEvery(pollTimeInSecs, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.alertIsPresent());
    }

    public static void sleep(int timOutInMillis) {
        try {
            Thread.sleep(timOutInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
