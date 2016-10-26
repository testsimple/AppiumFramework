package libraries.mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import libraries.utility.Log;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverFactory {

    /**
     * Return a mobile driver instance based on parameters
     * Initializing Appium client driver responsible for the connection to the Appium server
     */
    public static AppiumDriver<?> getInstance(String serverUrl,
                                           String platform,
                                           boolean realDevice,
                                           String iosDeviceName,
                                           String iosDeviceUdid,
                                           String androidDeviceName,
                                           String appPackage,
                                           String appActivity) {
        Log.debug(String.format("Start initializing Appium driver for %s platform.", platform.toUpperCase()));
        try {
            return buildDriver(serverUrl, platform, realDevice, iosDeviceName, iosDeviceUdid, androidDeviceName, appPackage, appActivity);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static AppiumDriver<?> buildDriver(String serverUrl,
                                            String platform,
                                            boolean realDevice,
                                            String iosDeviceName,
                                            String iosDeviceUdid,
                                            String androidDeviceName,
                                            String appPackage,
                                            String appActivity) throws MalformedURLException {
        AppiumDriver<?> driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", 60 * 5);

        switch (platform.toLowerCase()) {
            case "ios":
                if (realDevice) {
                    capabilities.setCapability("deviceName", iosDeviceName);
                    capabilities.setCapability("udid", iosDeviceUdid);
                    capabilities.setCapability("bundleId", appPackage);
                } else {

                }
                driver = new IOSDriver<>(new URL(serverUrl), capabilities);
                break;

            case "android":
                if (realDevice) {
                    capabilities.setCapability("deviceName", androidDeviceName);
                    capabilities.setCapability("androidPackage", appPackage);
                    capabilities.setCapability("androidActivity", appActivity);
                } else {

                }
                driver = new AndroidDriver<>(new URL(serverUrl), capabilities);
                break;
        }

        if (driver != null)
            Log.info(String.format("%s driver has been initialized successfully.", platform.toUpperCase()));
        else
            Log.info(String.format("Initializing %s driver failed.", platform.toUpperCase()));
        return driver;
    }

}
