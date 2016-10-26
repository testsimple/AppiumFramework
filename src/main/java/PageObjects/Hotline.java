package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class Hotline extends PageAbstract {

	// ScreenObject Factory
	@FindBy(id = "messsage")
	private WebElement txtMessage;
	
	@FindBy(id = "button1")
	private WebElement btnYES;
	
	@FindBy(id = "button2")
	private WebElement btnNO;

	public Hotline(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public TrangChu tapOn_NO_button() {
		waitVisible(btnNO);
		btnNO.click();
		Log.info("Tap on NO button");
		return new TrangChu(driver);
	}

	public Call tapOn_YES_button() {
		waitVisible(btnYES);
		btnYES.click();
		Log.info("Tap on YES button");
		return new Call(driver);
	}
	
	public String getMsg(){
		waitVisible(txtMessage);
		return txtMessage.getText();
	}

}
