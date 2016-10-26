package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class DangNhap extends PageAbstract {

	// ScreenObject Factory
	@FindBy(id = "btnLogin")
	private WebElement btnLogin;

	@FindBy(id = "btnRegister")
	private WebElement btnRegister;

	@FindBy(id = "btnForgotPassword")
	private WebElement btnForgot;

	@FindBy(id = "username")
	private WebElement txtUsr;

	@FindBy(id = "password")
	private WebElement txtPwd;

	public DangNhap(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public DangNhap input_Username(String username){
		waitVisible(txtUsr);
		txtUsr.sendKeys(username);
		Log.info("Username input: " + username);
		return this;
	}

	public DangNhap input_Password(String password){
		waitVisible(txtPwd);
		txtPwd.sendKeys(password);
		Log.info("Password input: " + password);
		return this;
	}

	public TrangChu tapOn_DangNhap(){
		waitVisible(btnLogin);
		btnLogin.click();
		Log.info("Tap on DangNhap button");
		return new TrangChu(driver);
	}
}
