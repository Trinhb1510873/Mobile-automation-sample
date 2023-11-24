package test.internationalization;

import io.appium.java_client.AppiumDriver;
import models.pages.login.EnterUsernameScreen;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.BaseTest;
import test_data.DataObjectBuilder;
import test_data.models.AccountData;
import test_flows.login.LoginFlow;

import java.io.IOException;

public class LanguageTest extends BaseTest {
    private AppiumDriver appiumDriver;

    @DataProvider
    public static AccountData[] accountData() {
        String filePath = "/src/main/java/test_data/login/AccountZiiChat.json";
        return DataObjectBuilder.buildDataObject(filePath, AccountData[].class);
    }

    @Test(dataProvider = "accountData", description = "i18n Android")
    public void testLanguageAndroid(AccountData accountData) throws InterruptedException, IOException {
        appiumDriver = getDriver();
        LoginFlow loginFlow = new LoginFlow(appiumDriver);
        loginFlow.gotoWelComeScreen();
        loginFlow.goToEnterUsernameScreen();

        EnterUsernameScreen enterUsernameScreen = new EnterUsernameScreen(appiumDriver);
        enterUsernameScreen.enterUserNameComponent().inputUsernameTxt(accountData.getUsername());
        enterUsernameScreen.enterUserNameComponent().clickOnContinueBtn();

        enterUsernameScreen.welcomeBackDialogComponent().clickOnContinueBtn();

        Thread.sleep(3000);
        String cmd = "adb -e emu finger touch 1";
        Runtime.getRuntime().exec(cmd.split(" ", 0));
        Thread.sleep(3000);
    }
}
