package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class ThuVienAnh extends PageAbstract {

	public ThuVienAnh(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	@FindBy(name = "Thư viện ảnh")
	private WebElement txtTitle;
	
	@FindBy(xpath = "//*[contains(@resource-id,'pp__thumbnail_image')]")
	private List<WebElement> imgThumbnail;

	@FindBy(id = "pp__btn_done")
	private WebElement btnCapNhat;
	
	@FindBy(id = "pp__btn_cancel")
	private WebElement btnHuy;

	@FindBy(id = "cwac_cam2_picture")
	private WebElement btnChupHinh;

	public String get_Title(){
		waitVisible(txtTitle);
		return txtTitle.getText();
	}
	
	public void select_Image(){
		System.out.println(imgThumbnail.size());
	}
	
	public DangTin tapOn_CapNhat(){
		waitVisible(btnCapNhat);
		btnCapNhat.click();
		return new DangTin(driver);
	}

	public DangTin tapOn_Huy(){
		waitVisible(btnHuy);
		btnHuy.click();
		return new DangTin(driver);
	}
	
	public DangTin tapOn_ChupHinh(){
		waitVisible(btnChupHinh);
		btnChupHinh.click();
		return new DangTin(driver);
	}
	
	public ThuVienAnh tapOn_ImageThumbnail(Integer vitri){
		waitVisible(imgThumbnail.get(0));
		if(vitri < 12){
			imgThumbnail.get(vitri).click();
		} else{
			Log.warn("--- Do not handle this situation yet!!! ---");
		}
		return this;
	}
	
	
}
