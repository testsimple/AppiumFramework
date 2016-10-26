package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class PageAbstract {
	protected static AppiumDriver<MobileElement> driver;

	public PageAbstract(AppiumDriver<MobileElement> driver) {
		PageAbstract.driver = driver;
		PageFactory.initElements(driver, this);
	}

	protected static void waitVisible(WebElement elWeb) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(elWeb));
	}

	protected static void waitClickable(WebElement elWeb) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(elWeb));
	}

	protected static void waitSelected(WebElement elWeb) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeSelected(elWeb));
	}

	protected static void waitChange(WebElement elWeb) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elWeb));
	}

	protected static void waitC(WebElement elWeb, String att, String val) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeContains(elWeb, att, val));
	}

	protected static void waitLocate(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
	}

	protected static void pressEnter() {
		((AndroidDriver<MobileElement>) driver).pressKeyCode(AndroidKeyCode.ENTER);
	}

	protected static void pressSearch() {
		((AndroidDriver<MobileElement>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
	}

	protected static void pressBack() {
		((AndroidDriver<MobileElement>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}

	protected void scrollRightToLeft(int x, int y) {
		int length = driver.manage().window().getSize().width - 1 ;
		TouchAction action = new TouchAction((MobileDriver) driver);
		action.longPress(length, y).moveTo(x, y).release().perform();
	}

	protected static void tapOn(int x, int y) {
		driver.tap(1, x, y, 1);
	}
}
