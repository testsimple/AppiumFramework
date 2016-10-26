package com.appium.test;

import org.testng.annotations.*;

import libraries.utility.Log;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One4 extends TestBase {

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

	@Test(groups = "function", description = "Do not contact to Hotline", priority = 0)
	public void testCase1() throws Exception {
		// Test-step:
		fromTrangChu.open_DanhMuc().open_Hotline().tapOn_NO_button();
	}

	@Test(groups = "function", description = "Want to contact to Hotline", priority = 1)
	public void testCase2() throws Exception {
		// Test-step:
		fromTrangChu.open_DanhMuc().open_Hotline().tapOn_YES_button();
	}
}
