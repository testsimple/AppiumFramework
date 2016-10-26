package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class ConfirmDangTin extends PageAbstract {
	public ConfirmDangTin(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//*[contains(@class,'WebView')]/android.view.View/android.view.View")
	private List<WebElement> webview;

	@FindBy(xpath = "//*[contains(@resource-id,'toolbar')]/child::*[3]")
	private WebElement toolBar;
	
	@FindBy(xpath = "//*[contains(@class,'WebView') and @scrollable='true']/android.view.View")
	private WebElement rsDesc;

	@FindBy(id = "tvUserName")
	private WebElement rsAccName;

	public String getWebTextThu(){
		waitVisible(webview.get(0));
		return webview.get(0).getAttribute("name");
	}

	public String get_ResultTitle() {
		waitVisible(toolBar);
		Log.info("Get Result-Title: " + toolBar.getText());
		return toolBar.getText();
	}
	
	public String get_AccountName() {
		waitVisible(rsAccName);
		Log.info("Get Account-Name: " + rsAccName.getText());
		return rsAccName.getText();
	}
	
	public String get_ResultChiTietTin() {
		waitVisible(rsDesc);
		Log.info("Get Desc-ChiTietTin: " + rsDesc.getAttribute("name"));
		return rsDesc.getAttribute("name");
	}
	
	public ConfirmDangTin get_Account() {
		get_AccountName();
		return this;
	}
	
	public ConfirmDangTin get_Title() {
		get_ResultTitle();
		return this;
	}
	
	
	
//		Log.info("Size: " + webview.size());
//		Log.info("Tag: " + webview.get(0).getTagName());
//		Log.info("Id: " + webview.get(0).getAttribute("resourceId"));
//		Log.info("ContenDesc by Name: " + webview.get(0).getAttribute("name"));
}
