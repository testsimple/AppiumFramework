package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import libraries.utility.Log;

public class SearchResult extends PageAbstract {

	// ScreenObject Factory
	@FindBy(id = "list_empty")
	private WebElement result;

	public SearchResult(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

	public String get_Result() {
		waitVisible(result);
		Log.info("Search result: " + result.getText());
		return result.getText();
	}

}
