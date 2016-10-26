package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class Modal extends PageAbstract {

	public Modal(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	@FindBy(name = "Chọn ảnh từ")
	private WebElement txtTieuDe;
	
	@FindBy(name = "Thư viện")
	private WebElement btnThuVien;
	
	@FindBy(name = "Camera")
	private WebElement btnCamera;
	
	@FindBy(id = "button1")
	private WebElement btnOK;
	
	// Method
	public String get_Title() {
		waitVisible(txtTieuDe);
		return txtTieuDe.getText();
	}
	
	public ThuVienAnh tapOn_ThuVien() {
		waitVisible(btnThuVien);
		btnThuVien.click();
		return new ThuVienAnh(driver);
	}
	
	public ConfirmDangTin tapOn_OK(){
		waitVisible(btnOK);
		btnOK.click();
		Log.info("Tap on button: OK");
		return new ConfirmDangTin(driver);
	}
	
	
}
