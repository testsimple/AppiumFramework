package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class TrangChu extends PageAbstract {

	// ScreenObject Factory
	@FindBy(id = "open")
	private WebElement btnSetting;

	@FindBy(id = "actionPost")
	private WebElement btnPost;
	
	@FindBy(id = "search_button")
	private WebElement btnSearch;

	@FindBy(xpath = "search_src_text")
	private WebElement txtSearch;

	@FindBy(id = "tabs")
	private WebElement tabBar;

	@FindBy(name = "Trang chủ")
	private WebElement trangchu;

	@FindBy(name = "Điện thoại")
	private WebElement dienthoai;

	@FindBy(xpath = "//*[contains(@resource-id,'tabs')]//android.widget.TextView[@index=3]")
	private List<WebElement> tags;

	public TrangChu(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public TrangChu tapOn_Search_buton() {
		waitClickable(btnSearch);
		btnSearch.click();
		Log.info("Tap on Search button");
		return new TrangChu(driver);
	}

	public TrangChu input_SearchText(String text) {
		pressSearch();
		driver.getKeyboard().sendKeys(text);
		Log.info("Search: " + text);
		return this;
	}
	
	public SearchResult submit_Search() {
		pressEnter();
		Log.info("Press ENTER keyboard");
		return new SearchResult(driver);
	}

	public DanhMuc open_DanhMuc() {
		waitClickable(btnSetting);
		btnSetting.click();
		Log.info("Tap on: icon DanhMuc");
		return new DanhMuc(driver);
	}
	
	public DangTin open_DangTin() {
		waitClickable(btnPost);
		btnPost.click();
		Log.info("Tap on: button DangTin");
		return new DangTin(driver);
	}
	
	public TrangChu tapOn_TabBar(String tag) {
		waitClickable(tabBar);
		Integer index = indexTag(tag);
		
		if(index != null){
			if(index > 3 && index < 8){
				scrollTab();
			} else if(index >= 8){
				scrollDouble();
			}
		}
		
		// Find Tag-Element
		tapOn_Tag(index);

		Log.info("Tap on: " + tag);
		return this;
	}
	
	public void tapOn_Tag(int index){
		WebElement eleTag = driver.findElementByXPath("//*[contains(@resource-id,'tabs')]//android.widget.TextView[@index=" + index + "]");
		Log.info("Tag-Name: " + eleTag.getText());
//		int xTouch = eleTag.getLocation().getX() + eleTag.getSize().getWidth()/2;
//		int yTouch = tabBar.getLocation().getY() + tabBar.getSize().getHeight()/2;
//		tapOn(xTouch, yTouch);
		eleTag.click();

		Log.info("IndexTag: " + index);
	}
	
	public Integer indexTag(String tag) {
		Integer index = null;
		String[] regexList = { "(.*)danhm[uụ]c(.*)",
				   			   "(.*)trangch[uủ](.*)",
				   			   "(.*)vi[eệ]cl[aà]m(.*)",
				   			   "(.*)[dđ]i[eệ]ntho[aạ]i(.*)",
				   			   "(.*)sims[oố][dđ][eẹ]p(.*)",
				   			   "(.*)m[aá]yt[ií]nh(.*)",
				   			   "(.*)b[aấ]t[dđ][oộ]ngs[aả]n(.*)",
				   			   "(.*)th[oờ]itrang(.*)",
				   			   "(.*)chimc[aả]nh(.*)",
				   			   "(.*)c[aá]cm[ụu]ckh[aá]c(.*)"
	 			 };
		for (int i = 0; i < regexList.length; i++) {
			if (tag.replaceAll("\\s", "").toLowerCase().matches(regexList[i])) {
				index = i;
			}
		}
		return index;
	}
	
	public void scrollTab(){
		int xTouch = tabBar.getLocation().getX() + 1;
		int yTouch = tabBar.getLocation().getY() + tabBar.getSize().getHeight()/2;
		scrollRightToLeft(xTouch, yTouch);
	}
	
	public void scrollDouble(){
		scrollTab();
		scrollTab();
	}
}
