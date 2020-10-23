package managers;

import enums.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManager {

    private WebDriver driver;
    private DriverType driverType;

    public WebDriverManager() {
        driverType = FileReaderManager.getInstance().getFileUtils().getBrowser();
    }

    public WebDriver getDriver() {
        if (driver == null) driver = getWebDriver();
        return driver;
    }

    private WebDriver getWebDriver() {

        switch (driverType) {

            case CHROME:
                System.setProperty("webdriver.chrome.driver", "../lk_tester/src/main/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                driver.getWindowHandle();
                return driver;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "../lk_tester/src/main/resources/drivers/geckodriver.exe");
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
                System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "C:/temp/logs.txt");

                FirefoxOptions options = new FirefoxOptions();
                return driver = new FirefoxDriver(options);

            default:
                throw new IllegalArgumentException(String.format("Invalid browser requested - %s", driverType));
        }
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }

}
