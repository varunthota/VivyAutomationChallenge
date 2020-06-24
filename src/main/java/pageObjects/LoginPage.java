package pageObjects;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class LoginPage {

    //Concatenate driver
    public LoginPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Click Mobile Element button to accept consent
    @AndroidFindBys({@AndroidBy(id = "eu.uvita:id/reminders_permissions_allow")})
    public List<WebElement> buttonAcceptConsent;

    //Click Mobile Element button when you already have Vivy account
    @AndroidFindBys({@AndroidBy(id = "eu.uvita:id/tutorials_negative_action")})
    public List<WebElement> buttonHaveAccount;
    //WebElement buttonHaveAccount;

    //Login form Vivy email field
    @AndroidFindBy(id = "eu.uvita:id/email")
    public WebElement emailField;

    //Login form Vivy password field
    @AndroidFindBy(id = "eu.uvita:id/password")
    public WebElement passwordField;

    //To click login button
    @AndroidFindBy(id = "eu.uvita:id/login_fragment_sign_in_button")
    public WebElement singInButton;

    //Toast message element when entered invalid credentials
    @AndroidFindBy(id = "eu.uvita:id/snackbar_text")
    public WebElement snackBarText;

    //Button to confirm SMS pin
    @AndroidFindBy(id = "eu.uvita:id/inputPIN")
    public WebElement buttonConfirmSMS;


    //Button to confirm SMS pin
    @AndroidFindBy(id = "eu.uvita:id/button_confirm")
    public WebElement buttonSMSConfirm;

    //Button to confirm SMS pin
    @AndroidFindBys({@AndroidBy(xpath = "//android.widget.TextView[contains(@text,'ist dein')]")})
    public List<WebElement> locatorSMSNotification;

    //Locator to identity where is my private key? after login
    @AndroidFindBy(id = "eu.uvita:id/activity_import_encrypted_private_key_welcome_no_key")
    public List<WebElement> buttonNoKey;

    //Locator to Delete All Data and Start Over
    @AndroidFindBy(id = "eu.uvita:id/activity_no_key_available_button")
    public WebElement buttonDeleteAllData;

    //Locator to confirm data deletion
    @AndroidFindBy(id = "eu.uvita:id/fragment_import_encrypted_private_key_delete_data_delete_button")
    public WebElement buttonConfirmDataDeletion;

    //Locator to skip Private key page
    @AndroidFindBy(id = "eu.uvita:id/activity_export_private_key_skip")
    public WebElement buttonSkipPrivateKey;


    public boolean checkElementExistence(List<WebElement> elementList) {
        return elementList.size() > 0 ? true : false;
    }

    public String getSMSPin() {
        return locatorSMSNotification.get(0).getText().split(" ist")[0];
    }


    //Delete data if seen 'where is my private key'
    public void actionIfDeletePrivateKeyVisible(TouchAction t){
        if (checkElementExistence(buttonNoKey) == true) {
            t.tap(tapOptions().withElement(element(buttonNoKey.get(0)))).perform();
            t.tap(tapOptions().withElement(element(buttonDeleteAllData))).perform();
            t.tap(tapOptions().withElement(element(buttonConfirmDataDeletion))).perform();
            t.tap(tapOptions().withElement(element(buttonSkipPrivateKey))).perform();
        }
    }

    //Automatic pin entry when notifications appear else manual entry by the user
    public void getPinAndEnter(AndroidDriver driver) throws InterruptedException {
        Thread.sleep(6000);
        driver.openNotifications();
        Thread.sleep(1500);
        if (checkElementExistence(locatorSMSNotification) == true) {
        String pin = getSMSPin();
        driver.navigate().back();
        Thread.sleep(2000);
            for (char ch : pin.toCharArray()) {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + ch)));
            }
        } else {
            driver.navigate().back();
            System.out.println("Please enter the pin manually");
            Thread.sleep(6000);
        }
        Thread.sleep(2000);
    }


}
