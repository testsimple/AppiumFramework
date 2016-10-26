package PageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import libraries.utility.Common;
import libraries.utility.Log;

public class DanhMucChinh extends PageAbstract {

	protected static Integer indexChinh = null;

	@AndroidFindBy(id = "wheel")
	@CacheLookup
	private WebElement lblDanhMucChinh;

	public DanhMucChinh(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(PageAbstract.driver, 30, TimeUnit.SECONDS), this);
	}

	public DanhMucPhu select_MucChinh(String mucchinh) {
		waitVisible(lblDanhMucChinh);
		chonMuc(mucchinh);
		if ((indexChinh != null && indexChinh != 2) || (indexChinh != null && indexChinh != 6)) {
			tapOn(lblDanhMucChinh);
			Log.info("Tap on MucChinh: \"" + mucchinh + "\"");
			return new DanhMucPhu(driver);
		}
		return new DanhMucPhu(driver);
	}

	public DangTin select_MucChinhOnly(String mucchinh) {
		waitVisible(lblDanhMucChinh);
		chonMuc(mucchinh.toString());
		Common.wait(1);
		if (indexChinh == 2 || indexChinh == 6) {
			tapOn(lblDanhMucChinh);
			Log.info("Tap on MucChinh: \"" + mucchinh + "\"");
		}
		return new DangTin(driver);
	}
	
	
	public void tapOn(WebElement lblElement) {
		int xCenter = lblElement.getLocation().getX() + lblElement.getSize().getWidth() / 2;
		int yTap = lblElement.getLocation().getY() + lblElement.getSize().getHeight() / 2;
		tapOn(xCenter, yTap);
//		driver.tap(1, xCenter, yTap, 1);
	}
	
	public void tapToExit() {
//		driver.manage().window().getSize().width/2
		driver.tap(1, 0 , 100, 1);
	}

	public static Integer indexMuc(String mucchon) {
		Integer index = null;
		String[] regexList = { "vi[eệ]cl[aà]m", 
							   "[dđ]i[eệ]ntho[aạ]i", 
							   "sims[oố][dđ][eẹ]p", 
							   "m[aá]yt[ií]nh",
							   "b[aấ]t[dđ][oộ]ngs[aả]n", 
							   "th[oờ]itrang", 
							   "chimc[aả]nh", 
							   "c[aá]cm[ụu]ckh[aá]c" 
							 };

		for (int i = 0; i < regexList.length; i++) {
			if (mucchon.replaceAll("\\s", "").toLowerCase().matches(regexList[i])) {
				index = i;
			}
		}
		return index;
	}

	public void chonMuc(String mucchon) {
		indexChinh = indexMuc(mucchon);
		if (indexChinh != null) {
			// System.out.println(driver.manage().window().getSize());
			// System.out.println(lblDanhMucChinh.getSize());
			scrollTo(indexChinh, lblDanhMucChinh);
			Log.info("Scroll to: " + mucchon);
		} else {
			Log.warn("--- \"" + mucchon + "\" is Invalid. Check again!!! ---");
		}
	}

	
	/**
	 * Height of Screen = 900, contain 6.6 items of List -> 135
	 * Height of Screen = 1200, contain 7.6 items of List -> 157
	 */
	public void scrollTo(int fieldIndex, WebElement element) {
		// Define start point to scroll
		int x = element.getLocation().getX() + element.getSize().getWidth() / 2;
		int y = element.getLocation().getY() + element.getSize().getHeight() - 1;

		int length = 0;
		int height = element.getSize().getHeight();
		// Distance syntax for scroll with Deviation: 7*(7-index)
		switch (height) {
		case 600:
			length = y - (81 * fieldIndex) - (7 * (7 - fieldIndex));
			break;
		case 900:
			length = y - (135 * fieldIndex) - (7 * (7 - fieldIndex));
			break;
		case 1200:
			length = y - (157 * fieldIndex) - (7 * (7 - fieldIndex));
			break;
		}

		TouchAction action = new TouchAction((MobileDriver) driver);
		action.longPress(x, y).moveTo(x, length).release().perform();
	}

	public void scrollMaximum(WebElement element) {
		scrollTo(7, element);
	}
}
