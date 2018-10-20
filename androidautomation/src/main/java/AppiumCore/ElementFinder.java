package AppiumCore;

import io.appium.java_client.android.AndroidDriver;

public class ElementFinder {

	private String locator = "new UiSelector()";
	private static AndroidDriver driver;
	
	public ElementFinder() {
		driver = Android.driver;
	}
	
	public ElementFinder resourceId(String value) {
		locator += ".resourceId(\""+value+"\")";
		return this;
	}
	
	public ElementFinder className(String value) {
		locator += ".className(\""+value+"\")";
		return this;
	}

	public ElementFinder text(String value) {
		locator += ".text(\""+value+"\")";
		return this;
	}
	
	public ElementAction makeUiElement() {
		return new ElementAction(locator);
	}
}
