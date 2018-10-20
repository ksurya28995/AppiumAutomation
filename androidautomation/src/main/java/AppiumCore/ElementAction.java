package AppiumCore;

import java.util.List;

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
		if (locator.contains("new UiSelector()"))
			return Android.driver.findElementByAndroidUIAutomator(locator);
		else
			return Android.driver.findElementByXPath(locator);
	}

	public List<WebElement> getElements() throws InterruptedException {
		return Android.driver.findElementsByXPath(locator);
	}

	public void tap() throws InterruptedException {
		getElement().click();
	}

	public void enterText(String value) throws InterruptedException {
		getElement().sendKeys("");
		getElement().sendKeys(value);
	}

	public String[] getAttributeList(String attribute) throws InterruptedException {
		String[] array = null;
		List<WebElement> elems = getElements();
		array = new String[elems.size()];
		for (int i = 0; i < elems.size(); i++)
			array[i] = elems.get(i).getAttribute(attribute);
		return array;
	}

	public String[] getTextList() throws InterruptedException {
		String[] array = null;
		List<WebElement> elems = getElements();
		array = new String[elems.size()];
		for (int i = 0; i < elems.size(); i++)
			array[i] = elems.get(i).getText();
		return array;

	}
	
	public String getText() throws InterruptedException {
		return getElement().getText();
	}
	// driver.pressKeyCode(AndroidKeyCode.HOME);
	
}
