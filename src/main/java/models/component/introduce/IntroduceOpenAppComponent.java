package models.component.introduce;

import common.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class IntroduceOpenAppComponent extends GlobalConstants {
    private final AppiumDriver appiumDriver;
    @AndroidFindBy(id = "com.ziichat.android.media:id/title")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement titleTxtElem;
    @AndroidFindBy(xpath = "//android.widget.TextView[2]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement subTitleTxtElem;
    @AndroidFindBy(id = "com.ziichat.android.media:id/buttonContinue")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueBtnElem;


    public IntroduceOpenAppComponent(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(TIMEOUT_GET_ELEMENT)), this);
    }

    @Step("Get Title text")
    public String getTitleTxt() {
        return titleTxtElem.getText();
    }

    @Step("Get Sub-Title text")
    public String getSubTitleTxt() {
        return subTitleTxtElem.getText();
    }

    @Step("Click on Continue button")
    public void clickContinueBtn() {
        continueBtnElem.click();
    }
}
