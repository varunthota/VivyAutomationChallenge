package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage {


    //Concatenate driver
    public DashBoardPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    //Menu button to book doctor Appointment
    @AndroidFindBy(id = "eu.uvita:id/fab_expand_menu_button")
    public WebElement buttonMenu;

    //Side Menu button to view my own profile
    @AndroidFindBy(xpath = "//*[@content-desc='Navigate up']")
    public WebElement buttonMyProfile;

    //Dashboard Toolbar
    @AndroidFindBy(id = "eu.uvita:id/toolbar")
    public WebElement toolbar;


}
