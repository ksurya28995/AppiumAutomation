package AppiumCore;

import org.openqa.selenium.WebElement;

public class ElementAction {

	public String locator;

	public ElementAction(String _locator) {
		locator = _locator;
	}

	public void threadSleep(int sleepTime) throws InterruptedException {
		Thread.sleep(sleepTime);
	}

	public void threadSleep() throws InterruptedException {
		Thread.sleep(2000);
	}

	public WebElement getElement() throws InterruptedException {
		if(!locator.contains("UiSelector"))
			return Android.driver.findElementByAndroidUIAutomator(locator);
		else 
			return Android.driver.findElementByXPath(locator);
	}
	
	public void tap() throws InterruptedException {
		getElement().click();
	}

	public void enterText(String value) throws InterruptedException {
		getElement().sendKeys("");
		getElement().sendKeys(value);
	}

}
