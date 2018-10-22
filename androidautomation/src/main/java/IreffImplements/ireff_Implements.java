package IreffImplements;

import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import AppiumCore.CsvHandler;
import AppiumCore.ElementFinder;

public class ireff_Implements {
	private static CsvHandler csvObj = null;
	private Map<String, String> arrData = new HashMap<String, String>();
	private ElementFinder elem = new ElementFinder();

	public ireff_Implements() {
		csvObj = new CsvHandler();
	}

	public void validIreff(String supportCSV) throws Exception {
		validNewOperatorsList(supportCSV);
		validOperaTabs(supportCSV);

	}
	
	public void validNewOperatorsList(String supportCSV) throws Exception {
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
		assertTrue("Some Operator not found", testCount == arrList.length);
		elem.contDesc("Navigate up").makeUiElement().tap();
		elem.resourceId("in.ireff.android:id/currentServiceLayout").makeUiElement().isDisplayed();
	}

	public void validOperaTabs(String supportCSV) throws Exception {
		arrData = csvObj.readCsvData(supportCSV);
		String[] csvTabNames = arrData.get("MainTabNames").split("@");
		String tabPath = "//android.widget.HorizontalScrollView[@resource-id=\"in.ireff.android:id/sliding_tabs\"]";
		String uiTabName = "";
		for(int i=0;i<csvTabNames.length;i++) {
			System.out.println(csvTabNames[i]);
			if (!elem.resourceId("android:id/text1").text(csvTabNames[i]).makeUiElement().isExist())
				elem.resourceId("android:id/text1").text(csvTabNames[i]).makeUiElement().scrollLeftToElem(tabPath, 3);
			uiTabName = elem.resourceId("android:id/text1").text(csvTabNames[i]).makeUiElement().getText();
			assertTrue("Some Tab were missing", csvTabNames[i].contains(uiTabName));
		}
	}
	
	
}
