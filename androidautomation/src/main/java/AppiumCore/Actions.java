package AppiumCore;


import org.openqa.selenium.interactions.touch.TouchActions;

import io.appium.java_client.android.AndroidKeyCode;

public class Actions {

	public void home() {
		Android.driver.pressKeyCode(AndroidKeyCode.HOME);
	}
	
	public void back() {
		Android.driver.pressKeyCode(AndroidKeyCode.BACK);
	}
	
	public void enter() {
		Android.driver.pressKeyCode(AndroidKeyCode.ENTER);
	}
	
	/*public void scrollUp(){
		TouchActions action = new TouchActions(Android.driver);
		action.scroll(,50, 0);
	}*/
	
	public void scrollDown() {
		Android.driver.pressKeyCode(AndroidKeyCode.ACTION_DOWN);
	}
	
}
