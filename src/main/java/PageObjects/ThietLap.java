package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Common;
import libraries.utility.Log;

public class ThietLap extends PageAbstract {

	// ScreenObject Factory
	@FindBy(id = "offline")
	private WebElement swOffline;
	
	public ThietLap(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public boolean isOn() {
		String isChecked = swOffline.getAttribute("checked");
		if(isChecked.equalsIgnoreCase("false")){
			return false;
		}
		return true;
	}

	public ThietLap turnOn_Offline() {
		if (!isOn()) {
			swOffline.click();
		}
		Log.info("Offline is Turn-On " + swOffline.getAttribute("checked"));
		Common.wait(3);
		return this;
	}
	
	public ThietLap turnOff_Offline() {
		if (isOn()) {
			swOffline.click();
		}
		Log.info("Offline is Turn Off " + swOffline.getAttribute("checked"));
		Common.wait(3);
		return this;
	}
}
