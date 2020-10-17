package Utils;

import enums.DriverType;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileUtils {

    private Properties props;
    private final String propertyFilePath = "../lk_tester/src/main/resources/webconfigs.properties";

    public ConfigFileUtils() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            props = new Properties();
            try {
                props.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("webconfigs.properties not found at " + propertyFilePath);
        }
    }

    public long getImplicitlyWait() {
        String implicitlyWait = props.getProperty("implicitlyWait");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the webconfigs.properties.");
    }

    public String getApplicationUrl() {
        String url = props.getProperty("demoblaze_url");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the webconfigs.properties.");
    }

    public DriverType getBrowser() {
        String browserName = props.getProperty("browser");
        if (browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
        else if (browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
        else
            throw new IllegalArgumentException("Invalid Browser Name in webconfigs.properties : " + browserName);
    }

    public Boolean getBrowserWindowMax() {
        String windowSize = props.getProperty("windowMaximize");
        if (windowSize != null) return Boolean.valueOf(windowSize);
        return true;
    }

}
