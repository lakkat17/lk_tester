package managers;

import Utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import java.net.URL;

public class BrowserManager {

    private String appURL;
    private Boolean windowMax;
    private WebDriver driver;

    public BrowserManager(WebDriver driver) {
        this.driver = driver;
        appURL = FileReaderManager.getInstance().getFileUtils().getApplicationUrl();
        windowMax = FileReaderManager.getInstance().getFileUtils().getBrowserWindowMax();
    }


    public String getAppUrl() {
        return appURL;
    }

    public void setIfWindowMax() {
        if (windowMax)
            driver.manage().window().maximize();
    }

    public void navigate_to_back_forth(String ops) {
        if (ops.equals("back"))
            driver.navigate().back();
        else if (ops.equals("forward"))
            driver.navigate().forward();
        else throw new IllegalArgumentException(String.format("Type of navigation is invalid - %s", ops));
        WaitUtils.sleep(1000);
    }

    public void navigate_to(URL url){
        driver.navigate().to(url);
    }

}
