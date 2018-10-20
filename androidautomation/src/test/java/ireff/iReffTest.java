package ireff;

import java.net.MalformedURLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import AppiumCore.Android;
import IreffImplements.ireff_Implements;
import io.appium.java_client.android.AndroidDriver;

public class iReffTest {
	
	private static AndroidDriver driver;
	
	@BeforeClass
	public static void init() throws MalformedURLException, InterruptedException {
		driver = Android.initDriver("ireff");
	}

	@Test
	public void startingTest() throws InterruptedException {
		ireff_Implements appObj = new ireff_Implements();
		appObj.validNewOperatorsList();
	}

	@AfterClass
	public static void terminate() throws MalformedURLException {
		Android.exitDriver();
	}
	
}
