package libraries.mobile;

import io.appium.java_client.AppiumDriver;
import libraries.utility.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MobileElementFinder {

    @Autowired
    private AppiumDriver<?> driver;

    private final int MAX_TIMEOUT = 60;

    /**
     * Example:
     * 1. processFindingElement("locator", "value")         : Find an element with a default timeout 60 seconds
     * 2. processFindingElement("locator", "value", 0)      : Find an element without any condition
     * 3. processFindingElement("locator", "value", 15)     : Find an element in 15 seconds
     */
    private WebElement processFindingElement(String locator, String value, Object... objects) {
        try {
            Object param;
            WebDriverWait wait = new WebDriverWait(driver, MAX_TIMEOUT); // Find an element with a default max timeout

            if (objects.length == 1) {
                param = objects[0];

                if ((int) param == 0) {
                    // Find an element without any condition
                    switch (locator) {
                        case "id":
                            return driver.findElementById(value);

                        case "name":
                            return driver.findElementByName(value);

                        case "xpath":
                            return driver.findElementByXPath(value);
                    }
                } else {
                    // Find an element with a defined timeout param
                    wait = new WebDriverWait(driver, (int) param);
                }
            }

            switch (locator) {
                case "id":
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(value)));

                case "name":
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(value)));

                case "xpath":
                    return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
            }
        } catch (Exception e) {
            Log.debug(String.format("!!! ERROR - Element not found with locator strategy: %s = %s", locator, value));
        }

        return null;
    }

    private List<WebElement> processFindingElements(String locator, String value, Object... objects) {
        try {
            Object param;
            List<WebElement> elementList;
            WebDriverWait wait = new WebDriverWait(driver, MAX_TIMEOUT); // Find an element with a default max timeout

            if (objects.length == 1) {
                param = objects[0];

                if ((int) param == 0) {
                    // Find elements without any condition
                    elementList = new ArrayList<>();
                    List<?> list = new ArrayList<>();

                    switch (locator) {
                        case "id":
                            list = driver.findElementsById(value);
                            break;

                        case "name":
                            list = driver.findElementsByName(value);
                            break;

                        case "xpath":
                            list = driver.findElementsByXPath(value);
                            break;
                    }
                    for (Object obj : list)
                        elementList.add((WebElement) obj);

                    return elementList;
                } else {
                    // Find an element with a defined timeout param
                    wait = new WebDriverWait(driver, (int) param);
                }
            }

            switch (locator) {
                case "id":
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(value)));

                case "name":
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(value)));

                case "xpath":
                    return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(value)));
            }
        } catch (Exception e) {
            Log.debug(String.format("!!! ERROR - Elements not found with locator strategy: %s = %s", locator, value));
        }

        return null;
    }

    /**
     * Find an element based on locator and property value provided
     */
    public WebElement findElementById(String id, Object... objects) {
        return processFindingElement("id", id, objects);
    }

    public WebElement findElementByName(String name, Object... objects) {
        return processFindingElement("name", name, objects);
    }

    public WebElement findElementByXPath(String xpath, Object... objects) {
        return processFindingElement("xpath", xpath, objects);
    }

    /**
     * Find multiple elements based on locator and property value provided
     */
    public List<WebElement> findElementsById(String id, Object... objects) {
        return processFindingElements("id", id, objects);
    }

    public List<WebElement> findElementsByName(String name, Object... objects) {
        return processFindingElements("name", name, objects);
    }

    public List<WebElement> findElementsByXPath(String xpath, Object... objects) {
        return processFindingElements("xpath", xpath, objects);
    }

}
