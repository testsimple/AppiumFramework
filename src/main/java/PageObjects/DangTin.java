package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class DangTin extends PageAbstract {
	
	@FindBy(id = "btnLogin")
	private static WebElement btnLogin;
	
	@FindBy(id = "edtTitle")
	private WebElement txtTieuDe;


	@FindBy(id = "actionMode")
	private WebElement btnChinhSua;

	@FindBy(id = "edtCate")
	private WebElement lblChonMuc;

	@FindBy(id = "place_holder")
	private WebElement btnImage;

	@FindBy(id = "description")
	private WebElement txtDescription;

	@FindBy(id = "newItem")
	private WebElement btnNewItem;

	@FindBy(id = "preview")
	private WebElement btnXemThu;

	@FindBy(id = "post")
	private WebElement btnDang;

	@FindBy(id = "image")
	private List<WebElement> imgThumbnail;
	
	public DangTin(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public DangTin input_TieuDe(String tieude) {
		waitVisible(txtTieuDe);
		txtTieuDe.sendKeys(tieude);
		return this;
	}
	
	public DangTin tapOn_ChinhSua() {
		waitVisible(btnChinhSua);
		btnChinhSua.click();;
		return new DangTin(driver);
	}

	public DanhMucChinh select_DanhMuc() {
		waitVisible(lblChonMuc);
		lblChonMuc.click();
		Log.info("Tap on: label DanhMuc");
		return new DanhMucChinh(driver);
	}

	public String get_SelectedDanhMuc() {
		waitVisible(lblChonMuc);
		Log.info("Selected DanhMuc: " + lblChonMuc.getText());
		return lblChonMuc.getText();
	}
	
	public Modal select_Image(){
		waitVisible(btnImage);
		btnImage.click();
		return new Modal(driver);
	}
	
	public DangTin send_Descript(String text){
		waitVisible(txtDescription);
		txtDescription.sendKeys(text);
		Log.info("Description: " + text);
		return this;
	}
	
	public DangTin tapOn_ThemNhomNoiDung() {
		waitVisible(btnNewItem);
		btnNewItem.click();
		return new DangTin(driver);
	}
	
	public ConfirmDangTin tapOn_DangTin(){
		waitVisible(btnDang);
		btnDang.click();
		Log.info("Tap on button: Đăng");
		return new ConfirmDangTin(driver);
	}


	public ConfirmDangTin tapOn_XemThu(){
		waitVisible(btnXemThu);
		btnXemThu.click();
		Log.info("Tap on button: Xem Thử");
		return new ConfirmDangTin(driver);
	}
	
}
