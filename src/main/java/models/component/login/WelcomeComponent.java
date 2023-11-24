package models.component.login;

import common.GlobalConstants;
import i18n.current_test.i18n;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class WelcomeComponent extends GlobalConstants {
	private final AppiumDriver appiumDriver;

	@AndroidFindBy(id = "com.ziichat.android.media:id/textWelcome")
	@iOSXCUITFindBy(iOSClassChain = "")
	public WebElement titleTxt;

	@AndroidFindBy(xpath = "//*[@text='" + i18n.I18N_733 + "']")
	@iOSXCUITFindBy(iOSClassChain = "")
	public WebElement subTitleTxt;

	@AndroidFindBy(id = "com.ziichat.android.media:id/btn_get_started")
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeButton[`name == '" + i18n.I18N_3 + "'`]")
	public WebElement startBtnElem;

	@AndroidFindBy(id = "com.ziichat.android.media:id/btnAccountRecovery")
	@iOSXCUITFindBy(iOSClassChain = "")
	public WebElement accountRecoveryLink;

	public WelcomeComponent(AppiumDriver appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(TIMEOUT_GET_ELEMENT)), this);

	}

	@Step("Click on Start button")
	public void clickOnStartBtn() {
		startBtnElem.click();
	}
}
