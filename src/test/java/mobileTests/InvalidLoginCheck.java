package mobileTests;

import core.BaseMobile;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.LoginPage;
import java.io.IOException;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static org.testng.AssertJUnit.assertEquals;

public class InvalidLoginCheck extends BaseMobile {

    @Test
    public void verifyInvalidLogin() throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = capabilities();
        LoginPage loginPage = new LoginPage(driver);
        TouchAction t = new TouchAction(driver);
        CommonPage cm = new CommonPage(driver);
        cm.clickIfElementVisible(loginPage.buttonHaveAccount, driver);
        loginPage.emailField.sendKeys(globalProperties("invalidEmail"));
        t.tap(tapOptions().withElement(element(loginPage.singInButton))).perform();
        cm.clickIfElementVisible(loginPage.buttonAcceptConsent, driver);
        t.tap(tapOptions().withElement(element(loginPage.singInButton))).perform();
        assertEquals(globalProperties("invalidLoginSnackBarText"), loginPage.snackBarText.getText());
        driver.quit();
    }

}
