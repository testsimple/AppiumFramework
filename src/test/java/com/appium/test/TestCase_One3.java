package com.appium.test;

import org.testng.annotations.*;
//import libraries.utility.*;

import libraries.utility.Log;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One3 extends TestBase {

	@BeforeSuite
	public void setUp() {
		config();
		Log.info("-----  Functional testing  -----");
	}

	@AfterSuite
	public void tearDown() {
		cleanAll();
	}

	@AfterMethod
	public void testBed() {
		resetApp();
	}

	@Test(groups = "function", description = "Open TrangChu", priority = 0)
	public void testCase1() throws Exception {
		// Test-step:
		fromTrangChu.open_DanhMuc().open_TrangChu();
	}

	@Test(groups = "function", description = "Open Hotline", priority = 1)
	public void testCase2() throws Exception {
		fromTrangChu.open_DanhMuc().open_Hotline();
	}

	@Test(groups = "function", description = "Open HuongDan", priority = 2)
	public void testCase3() throws Exception {
		fromTrangChu.open_DanhMuc().open_HuongDan();
	}

	@Test(groups = "function", description = "Open NoiQuy", priority = 3)
	public void testCase4() throws Exception {
		fromTrangChu.open_DanhMuc().open_NoiQuy();
	}

	@Test(groups = "function", description = "Open ThietLap", priority = 4)
	public void testCase5() throws Exception {
		fromTrangChu.open_DanhMuc().open_ThietLap();
	}

	@Test(groups = "function", description = "Open DangNhap", priority = 5)
	public void testCase6() throws Exception {
		fromTrangChu.open_DanhMuc().open_DangNhap();
	}

	@Test(groups = "function", description = "Open TurnOn-Off ThietLap", priority = 6)
	public void testCase7() throws Exception {
		fromTrangChu.open_DanhMuc().open_ThietLap()
									.turnOff_Offline()
									.turnOn_Offline()
									.turnOff_Offline()
									.turnOff_Offline()
									.turnOn_Offline()
									.turnOn_Offline();
	}

}
