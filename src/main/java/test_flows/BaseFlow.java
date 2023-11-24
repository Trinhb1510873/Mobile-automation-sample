package test_flows;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import models.pages.login.WelcomeScreen;

public class BaseFlow {
    protected final AppiumDriver appiumDriver;
    private String username;

    public BaseFlow(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    @Step("Go to Welcome screen")
    public void gotoWelComeScreen() {
        new WelcomeScreen(appiumDriver).introduceOpenAppComponent().clickContinueBtn();
    }

    @Step("Go to Enter username screen")
    public void goToEnterUsernameScreen() {
        new WelcomeScreen(appiumDriver).welcomeComponent().clickOnStartBtn();
    }
}
