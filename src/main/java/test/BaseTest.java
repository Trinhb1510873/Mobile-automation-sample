package test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.internal.CapabilityHelpers;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.testng.ITestResult;
import org.testng.annotations.Optional;
import org.testng.annotations.*;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {
	private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
	private static ThreadLocal<DriverFactory> driverThread;
	private static AppiumDriverLocalService service;
	private static String udid;
	private static String systemPort;
	private static String platformName;
	private static String platformVersion;
	private static String deviceName;
	private static String serverPort;
	private static String localeCode;

	@BeforeTest(description = "Start appium server at Before Test")
	@Parameters({"udid", "systemPort", "platformName", "platformVersion", "deviceName", "serverPort", "localeCode"})
	public void startAppiumService(String udid, String systemPort, String platformName, @Optional("platformVersion") String platformVersion, @Optional("deviceName") String deviceName, String serverPort, String localeCode) {
		BaseTest.udid = udid;
		BaseTest.systemPort = systemPort;
		BaseTest.platformName = platformName;
		BaseTest.platformVersion = platformVersion;
		BaseTest.deviceName = deviceName;
		BaseTest.serverPort = serverPort;
		BaseTest.localeCode = localeCode;


		startAppiumProgrammatically();
		driverThread = ThreadLocal.withInitial(() -> {
			DriverFactory driverThread = new DriverFactory();
			driverThreadPool.add(driverThread);
			return driverThread;
		});
	}

	@AfterMethod(description = "Capture screenshot")
	public void captureScreenshot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String testMethodName = result.getName();
			AppiumDriver currentDriverThread = driverThread.get().getDriver(Platform.valueOf(platformName), udid, systemPort, platformVersion, deviceName, serverPort, localeCode);
			Capabilities caps = currentDriverThread.getCapabilities();
			String currentUDID = CapabilityHelpers.getCapability(caps, "udid", String.class);
			String fileLocation = System.getProperty("user.dir") + "/screenshots/" + currentUDID + "-" + testMethodName + "-" + getTakenTime() + ".png";
			File screenshot = currentDriverThread.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshot, new File(fileLocation));
				Path screenshotPathContent = Paths.get(fileLocation);
				InputStream inputStream = Files.newInputStream(screenshotPathContent);
				Allure.addAttachment(testMethodName + '_' + getTakenTime(), inputStream);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@AfterTest(alwaysRun = true, description = "Quit the appium service at After Test")
	public void quitAppiumService() {
		driverThread.get().quitAppiumDriver();
		if (service != null) {
			service.stop();
			System.out.println("\n***************");
			System.out.println("APPIUM STOPPED!");
			System.out.println("***************");
		}
	}

	protected AppiumDriver getDriver() {
		return driverThread.get().getDriver(Platform.valueOf(platformName), udid, systemPort, platformVersion, deviceName, serverPort, localeCode);
	}

	private String getTakenTime() {
		Calendar calendar = new GregorianCalendar();
		int y = calendar.get(Calendar.YEAR);
		int m = calendar.get(Calendar.MONTH);
		int d = calendar.get(Calendar.DATE);
		int hr = calendar.get(Calendar.HOUR);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		return y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec;
	}

	private void startAppiumProgrammatically() {
		File fileLogLocation = new File(System.getProperty("user.dir") + "/server-logs/" + "server-log-" + getTakenTime() + ".log");
		if (fileLogLocation.getParentFile().exists()) {
			fileLogLocation.getParentFile().mkdirs();
		}
		service = new AppiumServiceBuilder()
				.withIPAddress("127.0.0.1")
				.usingPort(Integer.parseInt(serverPort))
				.withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromedriver_autodownload")
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withLogFile(fileLogLocation)
				.build();
		service.start();

		if (service.isRunning()) {
			System.out.println("\n***************");
			System.out.println("APPIUM STARTED!");
			System.out.println("I'm running at: " + new GregorianCalendar().getTime());
			System.out.println(udid + " || " + systemPort);
			System.out.println("***************\n");
		}

	}
}

