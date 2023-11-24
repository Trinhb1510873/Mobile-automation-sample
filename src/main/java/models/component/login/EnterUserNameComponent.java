package models.component.login;

import common.GlobalConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import i18n.current_test.i18n;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class EnterUserNameComponent extends GlobalConstants {
    private final AppiumDriver appiumDriver;

    @AndroidFindBy(id = "com.ziichat.android.media:id/txtUsername")
    @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
    public WebElement usernameTxtElem;

    @AndroidFindBy(id = "com.ziichat.android.media:id/btnContinue")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == '" + i18n.I18N_12 + "'`]")
    public WebElement continueBtnElem;

    public EnterUserNameComponent(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(TIMEOUT_GET_ELEMENT)), this);
    }

    @Step("Input username")
    public void inputUsernameTxt(String text) {
        usernameTxtElem.sendKeys(text);
    }

    @Step("Click on Continue button")
    public void clickOnContinueBtn() {
        continueBtnElem.click();
    }
}
