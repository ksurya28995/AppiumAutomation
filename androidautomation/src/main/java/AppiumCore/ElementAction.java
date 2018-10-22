package AppiumCore;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;

public class ElementAction {

	public static String EAlocator;
	public static Date time = new Date();

	public ElementAction(String _locator) {
		EAlocator = _locator;
	}

	public void threadSleep(int sleepTime) throws Exception {
		Thread.sleep(sleepTime);
	}

	public void threadSleep() throws Exception {
		Thread.sleep(2000);
	}

	public WebElement getElement() throws Exception {
		ElementFinder.locator = "new UiSelector()";
		waitForElement();
		if (EAlocator.contains("new UiSelector()")) {
			return Android.driver.findElementByAndroidUIAutomator(EAlocator);

		} else {
			return Android.driver.findElementByXPath(EAlocator);
		}
	}

	public List<WebElement> getElements() throws Exception {
		ElementFinder.locator = "new UiSelector()";
		return Android.driver.findElementsByXPath(EAlocator);
	}

	public void tap() throws Exception {
		getElement().click();
	}

	public void enterText(String value) throws Exception {
		getElement().sendKeys("");
		getElement().sendKeys(value);
	}

	public String[] getAttributeList(String attribute) throws Exception {
		String[] array = null;
		List<WebElement> elems = getElements();
		array = new String[elems.size()];
		for (int i = 0; i < elems.size(); i++)
			array[i] = elems.get(i).getAttribute(attribute);
		return array;
	}

	public String[] getTextList() throws Exception {
		String[] array = null;
		List<WebElement> elems = getElements();
		array = new String[elems.size()];
		for (int i = 0; i < elems.size(); i++)
			array[i] = elems.get(i).getText();
		return array;

	}

	public String getText() throws Exception {
		return getElement().getText();
	}

	public void isDisplayed() throws Exception {
		getElement().isDisplayed();
	}

	public void clearText() throws Exception {
		getElement().clear();
	}

	public void longPress() throws Exception {
		TouchAction action = new TouchAction(Android.driver);
		action.longPress(getElement()).perform();
	}

	public void longPress(Duration sec) throws Exception {
		TouchAction action = new TouchAction(Android.driver);
		action.longPress(getElement(), sec).perform();
	}

	public boolean isExist() throws Exception {
		try {
			if (EAlocator.contains("new UiSelector()"))
				return Android.driver.findElementByAndroidUIAutomator(EAlocator).isDisplayed();
			else
				return Android.driver.findElementByXPath(EAlocator).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static long startTime = 0;

	public static long startTime() {
		time = new Date();
		if (startTime == 0)
			startTime = time.getTime();
		return startTime;
	}

	public static boolean expired() {
		time = new Date();
		boolean isExpired = (time.getTime() - startTime()) > Android.waitTime;
		return isExpired;
	}

	public void waitForElement() throws Exception {
		boolean elemNotFound = true;
		while (!expired()) {
			if (isExist()) {
				elemNotFound = false;
				break;
			} else {
				Thread.sleep(500);
			}
		}
		if (elemNotFound) {
			throw new Exception("Element not found: " + EAlocator);
		}
	}

	public void hover() throws Exception {
		TouchAction action = new TouchAction(Android.driver);
		action.moveTo(getElement()).perform();
	}

	public ElementAction scrollLeftToElem(String scrollElemXpath, int noOfScroll) throws Exception {
		TouchAction act = new TouchAction(Android.driver);
		Point pt = Android.driver.findElementByXPath(scrollElemXpath).getLocation();
		pt.x = pt.getX() + 10;
		pt.y = pt.getY() + 10;
		while (!isExist() && noOfScroll > 0) {
			System.out.println("scrolling");
			act.press(pt.getX() + 250, pt.getY()).waitAction(Duration.ofMillis(500)).moveTo(pt.getX(), pt.getY())
					.release().perform();
			noOfScroll--;
		}

		return new ElementAction(EAlocator);
	}
}
