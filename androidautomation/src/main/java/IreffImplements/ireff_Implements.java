package IreffImplements;

import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import AppiumCore.Android;
import AppiumCore.CsvHandler;
import AppiumCore.ElementFinder;
import io.appium.java_client.android.AndroidDriver;

public class ireff_Implements {
	private static AndroidDriver driver;
	private static CsvHandler csvObj = null;
	private static Map<String, String> arrData = new HashMap<String, String>();

	public ireff_Implements() {
		driver = Android.driver;
		csvObj = new CsvHandler();
	}

	public String newOperListPath = "";

	public void validNewOperatorsList(String supportCSV) throws Exception {
		ElementFinder elem = new ElementFinder();
		arrData = csvObj.readCsvData(supportCSV);
		elem.resourceId("in.ireff.android:id/imageView1").makeUiElement().tap();
		String[] operList = elem.xpath(
				"//android.widget.LinearLayout[contains(@resource-id,'in.ireff.android:id/layout')]/android.widget.ImageView")
				.makeUiElement().getAttributeList("resourceId");
		String[] arrList = arrData.get("OperatorLists").split("@");
		int testCount = 0;
		for (String uiList : operList) {
			for (String csvList : arrList) {
				uiList = uiList.replaceAll("in.ireff.android:id/imageView", "");
				if (uiList.equalsIgnoreCase(csvList))
					testCount++;
			}
		}
		assertTrue("Some Operator not found", testCount==arrList.length);
	}

}
