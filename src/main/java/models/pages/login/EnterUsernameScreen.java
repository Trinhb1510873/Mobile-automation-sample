package models.pages.login;

import io.appium.java_client.AppiumDriver;
import models.component.login.EnterUserNameComponent;
import models.component.login.WelcomeBackDialogComponent;

public class EnterUsernameScreen {
    public final AppiumDriver appiumDriver;

    public EnterUsernameScreen(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public EnterUserNameComponent enterUserNameComponent() {
        return new EnterUserNameComponent(appiumDriver);
    }

    public WelcomeBackDialogComponent welcomeBackDialogComponent() {
        return new WelcomeBackDialogComponent(appiumDriver);
    }
}
