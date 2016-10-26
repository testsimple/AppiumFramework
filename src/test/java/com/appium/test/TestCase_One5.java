package com.appium.test;

import org.testng.annotations.*;

import libraries.utility.Common;
import libraries.utility.Log;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One5 extends TestBase {

	@BeforeSuite
	public void setUp() {
		config();
	}

	@AfterSuite
	public void tearDown() {
		cleanAll();
	}

	@BeforeMethod
	public void testBed() {
		resetApp();
		Log.info("-----  Functional testing  -----");
	}

	@Test(groups = "function", description = "Dang nhap khong thanh cong", priority = 0)
	public void testCase1() throws Exception {
		// Test-step:
		fromTrangChu.open_DanhMuc().open_DangNhap().input_Username("TanDung").input_Password("BiMatDaBiMat").tapOn_DangNhap();
		Common.wait(5);
	}

	@Test(groups = "function", description = "Dang nhap thanh cong", priority = 1)
	public void testCase2() throws Exception {
		// Test-step:
		fromTrangChu.open_DanhMuc().open_DangNhap().input_Username("xxx-account-xxx").input_Password("xxx-123-xxx").tapOn_DangNhap();
		Common.wait(5);
	}
}