package mobileTests;

import core.BaseMobile;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import pageObjects.CommonPage;
import pageObjects.DashBoardPage;
import pageObjects.LoginPage;
import java.io.IOException;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ValidLoginCheck extends BaseMobile {

    @Test
    public void verifyValidLogin() throws IOException, InterruptedException {
        AndroidDriver<AndroidElement> driver = capabilities();
        LoginPage loginPage = new LoginPage(driver);
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        CommonPage cm = new CommonPage(driver);
        TouchAction t = new TouchAction(driver);
        cm.clickIfElementVisible(loginPage.buttonHaveAccount, driver);
        loginPage.emailField.sendKeys(globalProperties("validEmail"));
        loginPage.passwordField.sendKeys(globalProperties("validPassword"));
        t.tap(tapOptions().withElement(element(loginPage.singInButton))).perform();
        cm.clickIfElementVisible(loginPage.buttonAcceptConsent, driver);
        t.tap(tapOptions().withElement(element(loginPage.singInButton))).perform();
        loginPage.getPinAndEnter(driver);
        t.tap(tapOptions().withElement(element(loginPage.buttonSMSConfirm))).perform();
        loginPage.actionIfDeletePrivateKeyVisible(t);
        assertEquals(true, dashBoardPage.buttonMenu.isDisplayed());
        assertEquals(true, dashBoardPage.buttonMyProfile.isDisplayed());
        assertTrue(dashBoardPage.toolbar.isDisplayed());
        driver.quit();
    }
}
