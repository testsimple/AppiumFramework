package com.appium.test;

import libraries.utility.Common;
import org.testng.annotations.*;
import io.appium.java_client.android.AndroidDriver;
import libraries.utility.Log;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One8 extends TestBase {

	@BeforeClass
	public void setUp() {
		config();
	}

	@AfterClass
	public void tearDown() {
		clean();
		Log.info("-----  End TestCase_One8 Class  -----");
	}

	@BeforeMethod
	public void testBed() {
		resetApp();
		Log.info("-----  Functional testing  -----");
	}

	@AfterMethod
	public void testClean() {
		closeApp();
//		clean();
		Log.info("-----  End Method  -----");
	}

	@Test(groups = "function", description = "Dang Tin" )
	public void testCase() throws Exception {

		//chovinh.arisvn.com.chovinhcom.views.activities.SplashScreen


//		((AndroidDriver) driver).startActivity("chovinh.arisvn.com.chovinhcom",".views.activities.SplashScreen");



		// 1. Đăng nhập
		fromTrangChu.open_DanhMuc().open_DangNhap()
								   .input_Username("xxx-account-xxx")
								   .input_Password("xxx-123-xxx")
								   .tapOn_DangNhap();
		Common.wait(5);


//		((AndroidDriver) driver).startActivity("chovinh.arisvn.com.chovinhcom","chovinh.arisvn.com.chovinhcom.views.activities.SplashScreen", null, null);
		((AndroidDriver<?>) driver).startActivity("chovinh.arisvn.com.chovinhcom",".views.activities.SplashScreen");


//		System.out.println(((AndroidDriver) driver).currentActivity());
	}

	@DataProvider(name = "dataset")
	public static Object[][] forSearch() {
		return new Object[][]{
				{"tieu de 1", "các mục khác", "mẹ và bé"},
				{"tieu de 2", "các mục khác", "du học"}};
	}
}
	
