package models.pages.login;

import io.appium.java_client.AppiumDriver;
import models.component.introduce.IntroduceOpenAppComponent;
import models.component.login.WelcomeComponent;

public class WelcomeScreen {
    private final AppiumDriver appiumDriver;

    public WelcomeScreen(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public WelcomeComponent welcomeComponent() {
        return new WelcomeComponent(appiumDriver);
    }

    public IntroduceOpenAppComponent introduceOpenAppComponent() {
        return new IntroduceOpenAppComponent(appiumDriver);
    }
}
