package pageObjects;


import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class CommonPage {


    //Concatenate driver
    public CommonPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Menu button to book doctor Appointment
    @AndroidFindBy(xpath = "//android.widget.ProgressBar")
    public List<WebElement> progressBar;

    public boolean checkElementExistence(List<WebElement> elementList) {
        return elementList.size() > 0 ? true : false;
    }

    public void clickIfElementVisible(List<WebElement> webElement, AndroidDriver driver) {
        if (checkElementExistence(webElement) == true) {
            TouchAction t = new TouchAction(driver);
            t.tap(tapOptions().withElement(element(webElement.get(0)))).perform();
        }
    }


}
