package IreffImplements;

import AppiumCore.Android;
import AppiumCore.ElementFinder;
import io.appium.java_client.android.AndroidDriver;

public class ireff_Implements {
	private static AndroidDriver driver;
	
	public ireff_Implements() {
		driver = Android.driver;
	}
	
	public String newOperListPath = "";
	public void validNewOperatorsList() throws InterruptedException {
		ElementFinder elem = new ElementFinder();
		Thread.sleep(15000);
		elem.resourceId("in.ireff.android:id/imageView1").makeUiElement().tap();
		
		//driver.pressKeyCode(AndroidKeyCode.HOME);
	}
}
