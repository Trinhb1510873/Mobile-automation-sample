package driver;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Platform;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class DriverFactory implements ZiiChatCapability {
	private AppiumDriver appiumDriver;

	public AppiumDriver getDriver(Platform platform, String udid, String systemPort, String platformVersion, String deviceName, String serverPort, String localeCode) {
		if (appiumDriver == null) {
			if (platform == null) {
				throw new IllegalArgumentException("Platform can't be null, you can provide one of these: " + Arrays.toString(Platform.values()));
			}
		}
		Exception exception = null;
		try {
			String urlStr = "http://localhost:" + serverPort + "/";
			URL targetServer = new URL(urlStr);
			String[] codes = localeCode.split("_");
			String language = codes[0];
			String locale = codes[1];

			switch (platform) {
				case ANDROID -> {
					UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
					uiAutomator2Options.setAutomationName("uiautomator2")
							.setUdid(udid)
							.setAppPackage(APP_PACKAGE_ZIICHAT_ANDROID)
							.setAppActivity(APP_ACTIVITY_ZIICHAT_ANDROID)
							.setSystemPort(Integer.parseInt(systemPort))
							.setLanguage(language)
							.setLocale(locale)
							.setIgnoreHiddenApiPolicyError(true)
							.setChromeOptions(ImmutableMap.of("w3c", false))
							.setAutoGrantPermissions(true);
					appiumDriver = new AndroidDriver(targetServer, uiAutomator2Options);
				}
				case IOS -> {
					XCUITestOptions xcuiTestOptions = new XCUITestOptions();
					xcuiTestOptions.setAutomationName("XCUITest")
							.setUdid(udid)
							.setDeviceName(deviceName)
							.setPlatformVersion(platformVersion)
							.setBundleId(BUNDLE_ID_ZIICHAT)
							.setLanguage(language)
							.setLocale(locale)
							.setWdaLocalPort(Integer.parseInt(systemPort))
							.setAutoAcceptAlerts(true);
					appiumDriver = new IOSDriver(targetServer, xcuiTestOptions);
				}
			}

		} catch (Exception e) {
			exception = e;
			freeSystemPort(systemPort);
		}

		if (appiumDriver == null) {
			throw new RuntimeException(exception.getMessage());
		}
		appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		return appiumDriver;
	}

	public void quitAppiumDriver() {
		if (appiumDriver != null) {
			appiumDriver.quit();
			appiumDriver = null;
		}
	}

	private void freeSystemPort(String systemPort) {
		try {
			String cmd = "npx kill-port " + systemPort;
			Runtime.getRuntime().exec(cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

