package test_flows.login;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import test_flows.BaseFlow;

public class LoginFlow extends BaseFlow {
    private final AppiumDriver appiumDriver;

    public LoginFlow(AppiumDriver appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }
}
