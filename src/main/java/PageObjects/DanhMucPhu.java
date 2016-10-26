package PageObjects;

import java.util.Set;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;
import libraries.utility.Utilities;

public class DanhMucPhu extends DanhMucChinh {
	private static Integer indexPhu = null;
	private static final String path = System.getProperty("user.dir")
														+ "/src/main/java/PageObjects/config/danhmucphu.json";

	public DanhMucPhu(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	@FindBy(id = "title")
	private WebElement tDanhMucPhu;

	@FindBy(id = "back")
	private WebElement btnBack;

	@FindBy(id = "wheel")
	private WebElement lblDanhMucPhu;

	public DangTin select_MucPhu(String mucphu) {
		if (indexChinh != null) {
			waitVisible(lblDanhMucPhu);
			chonMuc(mucphu);
			if (indexPhu != null) {
				tapOn(lblDanhMucPhu);
				Log.info("Tap on MucPhu: " + mucphu);
			} else
			Log.warn("--- Select \"" + mucphu + "\" at MucPhu is not success!!! ---");
		} else {
			Log.warn("--- Select DanhMuc unsuccessfully!!! ---");
		}
		return new DangTin(driver);
	}

	public void chonMuc(String mucphu) {
		indexPhu = indexDanhMucPhu(mucphu);
		if(indexPhu != null){
			Integer arg = indexPhu / 7;
			
			// Syntax for scroll over 7 fields
			if (arg > 0) {
				while (arg > 0) {
					scrollMaximum(lblDanhMucPhu);
					arg--;
				}
			}
			scrollTo(indexPhu - (indexPhu / 7) * 7, lblDanhMucPhu);
			Log.info("Scroll to: \"" + mucphu + "\"");
		} else
		Log.warn("--- Does not exist MucPhu: \"" + mucphu + "\" ---");
	}

	public static Integer indexDanhMucPhu(String mucphu) {
		Integer index = null;
		String obj = Utilities.readJsonFile(path);

		// JSONObject objTong = new JSONObject(obj);
		// JSONObject objSelected = (JSONObject) objTong.getJSONObject(indexChinh.toString());
		try {
			JSONObject objSelected = new JSONObject(obj).getJSONObject(indexChinh.toString());
			// Regex with Key of JSONObject
			Set<String> keyset = objSelected.keySet();
			for (String l : keyset) {
				if (mucphu.replaceAll("\\s", "").toLowerCase().matches(l)) {
					index = objSelected.getInt(l);
				}
			}
			Log.info("Inputted MucPhu has been got index: " + index);
			return index;
		} catch (NullPointerException e) {
			Log.warn("--- \"" + mucphu + "\" is invalid. Please check again!!! ---");
		}
		return index;
	}

}
