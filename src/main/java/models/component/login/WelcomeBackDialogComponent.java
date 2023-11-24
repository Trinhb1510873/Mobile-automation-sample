package models.component.login;

import common.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class WelcomeBackDialogComponent extends GlobalConstants {

    private final AppiumDriver appiumDriver;

    @AndroidFindBy(id = "android:id/button1")
    public WebElement continueBtnElem;
    @AndroidFindBy(id = "com.google.android.gms:id/continue_button")
    public WebElement continueGGBtnElem;


    public WelcomeBackDialogComponent(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(TIMEOUT_GET_ELEMENT)), this);
    }

    @Step("Click on Continue Button")
    public void clickOnContinueBtn() {
        continueBtnElem.click();
    }

    @Step("Click on Continue GG Button")
    public void clickOnContinueGGBtn() {
        continueGGBtnElem.click();
    }
}
