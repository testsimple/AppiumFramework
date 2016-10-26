package libraries.mobile;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import libraries.utility.Common;

@Component
public class MobileAction {

	AppiumDriver<MobileElement> driver;
	MobileElementFinder mobileElementFinder;

	private TouchAction touchAction;

	public void initialize() {
		touchAction = new TouchAction(driver);
	}

	public TouchAction getTouchAction() {
		return touchAction;
	}

	public void tap(WebElement element) {
		element.click();
		Common.wait(1);
	}

	public void tap(int x, int y) {
		touchAction.tap(x, y).perform();
		Common.wait(1);
	}

	public void tapAndHold(WebElement element, int duration) {
		touchAction.longPress(element, duration * 1000).perform();
		Common.wait(1);
	}

	public void tapAndHold(int x, int y, int duration) {
		touchAction.longPress(x, y, duration * 1000).perform();
		Common.wait(1);
	}

	public void moveToElement(WebElement element) {
		touchAction.moveTo(element);
		Common.wait(1);
	}

	public void typeAndroid(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
		hideAndroidKeyboard();
		Common.wait(1);
	}

	public void typeAndroid(String text) {
		(new Actions(driver)).sendKeys(text).perform();
		hideAndroidKeyboard();
		Common.wait(1);
	}

	public void typeAndroidOnly(String text) {
		(new Actions(driver)).sendKeys(text).perform();
		Common.wait(1);
	}

	public void swipeRightToLeft(int startX, int startY, int length) {
		driver.swipe(startX, startY, startX - length, startY, 1000);
		Common.wait(1);
	}

	public void swipeLeftToRight(int startX, int startY, int length) {
		driver.swipe(startX, startY, startX + length, startY, 1000);
		Common.wait(1);
	}

	public void swipeBottomToTop(int startX, int startY, int length, Object... objects) {
		try {
			switch (objects.length) {
			case 0:
				driver.swipe(startX, startY, startX, startY - length, 1000);
				Common.wait(1);
				break;

			case 1:
				for (int i = 1; i <= (int) objects[0]; i++) {
					driver.swipe(startX, startY, startX, startY - length, 1000);
					Common.wait(1);
				}
				break;

			case 2:
				for (int i = 1; i <= (int) objects[0]; i++) {
					driver.swipe(startX, startY, startX, startY - length, (int) objects[1] * 1000);
					Common.wait(1);
				}
				break;
			}
		} catch (Exception e) {
		}
	}

	public void rotateScreenToPortrait() {
		driver.rotate(ScreenOrientation.PORTRAIT);
		Common.wait(2);
	}

	public void rotateScreenToLandscape() {
		driver.rotate(ScreenOrientation.LANDSCAPE);
		Common.wait(2);
	}

	public void hideAndroidKeyboard() {
		driver.hideKeyboard();
	}

}
