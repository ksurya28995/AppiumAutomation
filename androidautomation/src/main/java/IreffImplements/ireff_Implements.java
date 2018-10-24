package IreffImplements;

import static junit.framework.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

import AppiumCore.CsvHandler;
import AppiumCore.ElementFinder;
import junit.framework.Assert;

public class ireff_Implements {
	private static CsvHandler csvObj = null;
	private Map<String, String> arrData = new HashMap<String, String>();
	private ElementFinder elem = new ElementFinder();

	public ireff_Implements() {
		csvObj = new CsvHandler();
	}

	public void validIreff() throws Exception {
		// validNewOperatorsList("validLists.csv");
		// validOperaTabs("validLists.csv");
		validTabData("validTabData.csv");
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
		String[] operaList = arrData.get("OperatorLists").split("@");
		for (int i = 0; i < operaList.length; i++) {
			System.out.println("");
			System.out.println("Checking for " + operaList[i].toUpperCase());
			elem.resourceId("in.ireff.android:id/imageView1").makeUiElement().tap();
			elem.resourceId("in.ireff.android:id/imageView" + operaList[i]).makeUiElement().tap();
			if (operaList[i].equalsIgnoreCase("MTNL"))
				elem.resourceId("in.ireff.android:id/textView").text("Mumbai").makeUiElement().tap();
			else
				elem.resourceId("in.ireff.android:id/textView").text("Tamil Nadu").makeUiElement().tap();
			if (arrData.get(operaList[i] + "TabNames").equals("")) {
				String uiMsg = elem.resourceId("in.ireff.android:id/noContentMessage").makeUiElement().getText();
				assertTrue("Failed on No recharge plans screen",
						uiMsg.equalsIgnoreCase("No recharge plans for this operator and circle."));
				System.out.println(uiMsg);
			} else {
				String[] csvTabNames = arrData.get(operaList[i] + "TabNames").split("@");
				String tabPath = "//android.widget.HorizontalScrollView[@resource-id=\"in.ireff.android:id/sliding_tabs\"]";
				String uiTabName = "";
				for (int j = 0; j < csvTabNames.length; j++) {
					if (!elem.resourceId("android:id/text1").text(csvTabNames[j]).makeUiElement().isExist()) {
						elem.resourceId("android:id/text1").text(csvTabNames[j]).makeUiElement()
								.scrollLeftByCount(tabPath, 5);
					}
					uiTabName = elem.resourceId("android:id/text1").text(csvTabNames[j]).makeUiElement().getText();
					assertTrue("Some Tab were missing", csvTabNames[j].contains(uiTabName));
					System.out.println(csvTabNames[j]);
				}
			}
		}
	}

	public void validTabData(String supportCSV) throws Exception {
		arrData = csvObj.readCsvData(supportCSV);
		String[] tabLists = arrData.get("IdeaTabNames").split("@");
		List<WebElement> packs = null;
		String eachPackPath = null;
		String packPricePath = null;
		String packPrice = null;
		List<WebElement> eachPack = null;
		boolean flag;
		for (int i = 0; i < tabLists.length; i++) {
			elem.resourceId("android:id/text1").text(tabLists[i]).makeUiElement().tap();
			packs = elem.resourceId("in.ireff.android:id/resultLayout").makeUiElement().getElements();
			for (int j = 0; j < packs.size() - 1; j++) {
				eachPackPath = "//android.widget.LinearLayout[@resource-id=\"in.ireff.android:id/resultLayout\"][@index='"
						+ j + "']//android.widget.TextView[@resource-id=\"in.ireff.android:id/tag\"]";
				eachPack = elem.xpath(eachPackPath).makeUiElement().getElements();
				flag = false;
				for (WebElement eachElem : eachPack) {
					if (tabLists[i].equalsIgnoreCase("Topup"))
						if (eachElem.getText().equalsIgnoreCase(tabLists[i])
								|| eachElem.getText().equalsIgnoreCase("FULL TT")) {
							flag = true;
							break;
						} else {
							if (eachElem.getText().equalsIgnoreCase(tabLists[i])) {
								flag = true;
								break;
							}
						}
				}
				packPricePath = "//android.widget.LinearLayout[@resource-id=\"in.ireff.android:id/resultLayout\"][@index='"
						+ j + "']//android.widget.TextView[@resource-id=\"in.ireff.android:id/price\"]";
				packPrice = elem.xpath(packPricePath).makeUiElement().getText();
				if (!flag) {
					Assert.fail("Category is missing for Rs." + packPrice + " pack");
				}
				System.out.println("Passed: " + packPrice);
			}
		}
	}

}
