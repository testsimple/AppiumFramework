package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Common;
import libraries.utility.Log;

public class DanhMuc extends PageAbstract {

	// ScreenObject Factory
	@FindBy(id = "homepage")
	private WebElement btnTrangChu;

	@FindBy(name = "Thiết lập")
	private WebElement btnThietLap;
	
	@FindBy(name = "Nội quy")
	private WebElement btnNoiQuy;
	
	@FindBy(name = "Hướng dẫn")
	private WebElement btnHuongDan;
	
	@FindBy(name = "Hotline 0941958585")
	private WebElement btnHotline;
	
	@FindBy(name = "Đăng nhập")
	private WebElement btnDangNhap;
	
	public DanhMuc(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public TrangChu open_TrangChu() {
		waitVisible(btnTrangChu);
		btnTrangChu.click();
		Log.info("Tap on item: TrangChu");
		Common.wait(5);
		return new TrangChu(driver);
	}
	
	public ThietLap open_ThietLap() {
		waitVisible(btnThietLap);
		btnThietLap.click();
		Log.info("Tap on item: ThietLap");
		return new ThietLap(driver);
	}

	public NoiQuy open_NoiQuy() {
		waitVisible(btnNoiQuy);
		btnNoiQuy.click();
		Log.info("Tap on item: NoiQuy");
		return new NoiQuy(driver);
	}

	public HuongDan open_HuongDan() {
		waitVisible(btnHuongDan);
		btnHuongDan.click();
		Log.info("Tap on item: HuongDan");
		return new HuongDan(driver);
	}

	public Hotline open_Hotline() {
		waitVisible(btnHotline);
		btnHotline.click();
		Log.info("Tap on item: Hotline");
		return new Hotline(driver);
	}
	
	public DangNhap open_DangNhap() {
		waitVisible(btnDangNhap);
		btnDangNhap.click();
		Log.info("Tap on item: DangNhap");
		return new DangNhap(driver);
	}
	
}
