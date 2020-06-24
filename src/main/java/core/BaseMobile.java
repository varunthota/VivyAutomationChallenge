package core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseMobile {

    public static AndroidDriver<AndroidElement> capabilities() throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, globalProperties("deviceName"));
        cap.setCapability("appPackage", "eu.uvita");
        cap.setCapability("appActivity", "eu.uvita.ui.splash.SplashActivity");
        cap.setCapability("noReset", false);
        cap.setCapability("autoGrantPermissions", true);
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        return driver;
    }

    public static String globalProperties(String key) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
        Properties prop = new Properties();
        prop.load(fis);
        String value = (String) prop.get(key);
        return value;
    }


}
