package AppiumCore;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Android {

	public static AndroidDriver driver;
	public static int waitTime = 30;

	public static AndroidDriver initDriver(String appName) throws MalformedURLException, InterruptedException {
		driver = new AndroidDriver(new URL(PropertyManager.getPropertyValue("androiddriverurl")),
				getDevCapabilities(appName));
		Thread.sleep(7000);
		return driver;
	}
	
	public static DesiredCapabilities getDevCapabilities(String appName) {
		String appComName = appName + "package";
		String appMainActivity = appName + "mainactivity";
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(MobileCapabilityType.DEVICE_NAME, PropertyManager.getPropertyValue("devicename"));
		capability.setCapability(MobileCapabilityType.PLATFORM_VERSION, PropertyManager.getPropertyValue("platformversion"));
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
		capability.setCapability("noReset", "true");
		capability.setCapability("appPackage", PropertyManager.getPropertyValue(appComName.toLowerCase()));
		capability.setCapability("appActivity", PropertyManager.getPropertyValue(appMainActivity.toLowerCase()));
		return capability;
	}
	
	public static DesiredCapabilities getDrivCapabilities(String appName) {
		DesiredCapabilities capability = new DesiredCapabilities();
		String deviceName = "deviceName";
		String platformName = "platformName";
		String platformLevel = "platformLevel";
		capability.setCapability(deviceName, PropertyManager.getPropertyValue(deviceName.toLowerCase()));
		capability.setCapability(platformName, PropertyManager.getPropertyValue(platformName.toLowerCase()));
		capability.setCapability(platformLevel, PropertyManager.getPropertyValue(platformLevel.toLowerCase()));
		System.out.println(PropertyManager.getPropertyValue(platformLevel.toLowerCase()));
		capability.setCapability("platformVersion", "8.1.0");
		String appComName = appName + "package";
		System.out.println(appComName);
		String appMainActivity = appName + "mainactivity";
		capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
		capability.setCapability("noReset", "true");
		System.out.println(PropertyManager.getPropertyValue(appComName.toLowerCase()));
		System.out.println(PropertyManager.getPropertyValue(appMainActivity.toLowerCase()));
		
		capability.setCapability("appPackage", PropertyManager.getPropertyValue(appComName.toLowerCase()));
		capability.setCapability("appActivity", PropertyManager.getPropertyValue(appMainActivity.toLowerCase()));
		return capability;
	}

	public static void exitDriver() throws MalformedURLException {
		driver.pressKeyCode(AndroidKeyCode.HOME);
		System.err.println("Uninstalling appium support apps on the device..");
		driver.removeApp(PropertyManager.getPropertyValue("appiumsettingspackage"));
		System.out.println("Uninstall Appium Setting app : done");
		driver.removeApp(PropertyManager.getPropertyValue("appiumunlockpackage"));
		System.out.println("uninstall Appium Unlock app : done");
		driver.quit();
		System.out.println("Quiting the Application");
	}
}
