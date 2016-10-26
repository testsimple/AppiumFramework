package com.appium.test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import PageObjects.Call;
import PageObjects.ConfirmDangTin;
import PageObjects.DangTin;
import PageObjects.DanhMuc;
import PageObjects.DanhMucChinh;
import PageObjects.DanhMucPhu;
import PageObjects.Hotline;
import PageObjects.HuongDan;
import PageObjects.NoiQuy;
import PageObjects.SearchResult;
import PageObjects.ThietLap;
import PageObjects.TrangChu;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import libraries.mobile.AppiumServerManager;
import libraries.utility.ExcelUtils;
import libraries.utility.Log;

/**
 * Created by User on 5/27/2016.
 */
public class TestBase {

    // Constant
    public static final Integer MINUTE = 60;
    public static final Integer TIMEOUT = 5 * MINUTE;

    // Result
    public static String ActualResult;
    public static String ExpectedResult;

    // Driver
    protected static AppiumDriver<MobileElement> driver;

    // Appium params
    private static final String appiumFolder = "C:/Program Files (x86)/Appium";
    private static final String url = "http://localhost:4723/wd/hub";

    // AUT
    private static final File classpathRoot = new File(System.getProperty("user.dir"));
    private static final File appDir = new File(classpathRoot, "AUT");
    private static final File app = new File(appDir, "ChoVinh-xxx-xxx.apk");
    public static final String appPackage = "cpm.chovinh.arisvn";

    // Device params
    //private static final String deviceName = "LGD855890d40c7";
    private static final String deviceName = "192.168.56.101:5555";
    private static final String platformName = "Android";

    // Screens declaration
    protected static DanhMuc fromDanhMuc;
    protected static TrangChu fromTrangChu;
    protected static SearchResult fromSearchResult;
    protected static ThietLap fromThietLap;
    protected static HuongDan fromHuongDan;
    protected static Hotline fromHotline;
    protected static Call fromCall;
    protected static NoiQuy fromNoiQuy;
    protected static DangTin fromDangTin;
    protected static DanhMucChinh fromDanhMucChinh;
    protected static DanhMucPhu fromDanhMucPhu;
    protected static ConfirmDangTin fromConfirmDangTin;

    // Constructor
    public TestBase() {
        super();
    }

    // Initial
    public static void initScreen() {
        fromTrangChu = new TrangChu(driver);
        fromDanhMuc = new DanhMuc(driver);
        fromSearchResult = new SearchResult(driver);
        fromDangTin = new DangTin(driver);
        fromDanhMucChinh = new DanhMucChinh(driver);
        fromDanhMucPhu = new DanhMucPhu(driver);
        fromConfirmDangTin = new ConfirmDangTin(driver);
    }

    public static void config() {
        // Server set-up
        AppiumServerManager.setupConnection("127.0.0.1", "4750", appiumFolder);
        Log.info("Appium Server is " + AppiumServerManager.getInstance().isRunning());

        try {
            AppiumServerManager.getInstance().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.info("NTD Server is " + AppiumServerManager.getInstance().isRunning());
        installApp();
    }

    public static void installApp() {
        // Driver configuration
        configDriver();

        // Install AUT
        if (!driver.isAppInstalled(appPackage)) {
            Log.info("App is installing...");
            driver.installApp(app.getAbsolutePath());
        }

        // Screen-pages initial
        Log.info("Screen-pages initial");
        initScreen();
    }

    public static void resetApp() {
        if (!driver.isAppInstalled(appPackage)) {
            Log.info("App is installing...");
            driver.installApp(app.getAbsolutePath());
        }
        driver.resetApp();
        garbage();
        Log.info("App has been reset.");
    }

    public static void launchApp() {
        driver.launchApp();
        Log.info("Launch App.");
        garbage();
    }

    public static void closeApp() {
        driver.closeApp();
        garbage();
        Log.info("App has been closed.");
    }

    public static void closeSession() {
        driver.quit();
        garbage();
        Log.info("Session has been closed.");
    }

    public static void configDriver() {
        // Define Capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", platformName);
        cap.setCapability("appPackage", appPackage);
        cap.setCapability("deviceName", deviceName);
        cap.setCapability("newCommandTimeout", TIMEOUT);
        cap.setCapability("unicodeKeyboard", true);
        cap.setCapability("app", app.getAbsolutePath());
//		cap.setCapability("appActivity", "chovinh.arisvn.com.chovinhcom.views.activities.SplashScreen");

        Log.info("Please wait for config system...");

        // Install app to device
        try {
            driver = new AndroidDriver<MobileElement>(new URL(url), cap);
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            Log.info("Config Driver SUCCESSFULLY. Welcome to AUTOMaTioN TeST WoRDL.");

        } catch (Exception e) {

            Log.debug("Config Driver UN-SUCCESSFULLY. Please CHECK again.");

        }
    }

    public static void cleanAll() {
        uninstall();
        closeSession();
        try {
            AppiumServerManager.getInstance().stop();
            garbage();
            Log.info("-----  Exit testscript  -----");
        } catch (Exception e) {
            garbage();
            Log.info("-----  Stop testscript  -----");
        }
    }

    public static void clean() {
        closeSession();
        try {
            AppiumServerManager.getInstance().stop();
            garbage();
            Log.info("-----  Exit testscript  -----");
        } catch (Exception e) {
            garbage();
            Log.info("-----  Stop testscript  -----");
        }
    }

    public static void uninstall() {
        driver.removeApp(appPackage);
        Log.info("App has been removed!!!");
    }

    public static void garbage() {
        Runtime.getRuntime().gc();
    }

    public static void assertEqual(String rsActual, String rsExpected) {
        Assert.assertEquals(rsActual, rsExpected);
    }

    public static String getExpectedResult() {
        String path = System.getProperty("user.dir") + "/src/test/java/testdata/input.xlsx";
        ExcelUtils.openByPath(path);
        String sheetname = "Description";
        ExcelUtils.getSheet(sheetname);
        Log.info("Get Expected result from Excel file");
        return ExcelUtils.getCellValue(1, 1);
    }

    public static String getTieuDe() {
        String path = System.getProperty("user.dir") + "/src/test/java/testdata/input.xlsx";
        ExcelUtils.openByPath(path);
        String sheetname = "Description";
        ExcelUtils.getSheet(sheetname);
        return ExcelUtils.getCellValue(1, 2);
    }

    public static void login() {
    	resetApp();
    	TrangChu tc = new TrangChu(driver);
    	tc.open_DanhMuc().open_DangNhap()
                		 .input_Username("xxx-account-xxx")
                		 .input_Password("xxx-123-xxx")
                		 .tapOn_DangNhap();
    }

}
