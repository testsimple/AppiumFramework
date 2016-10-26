package com.appium.test;

import org.testng.annotations.*;
//import libraries.utility.*;

import libraries.utility.Log;


/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One2 extends TestBase {

	@BeforeSuite
	public void setUp() {
		config();
		Log.info("-----  Functional testing  -----");
	}

	@AfterSuite
	public void tearDown() {
		cleanAll();
	}

//	@AfterClass
//	public void closeDriver() {
//		closeApp();
//	}

	@Test
	public void testCase1() throws Exception {

		// Test-step:
//		fromTrangChu.open_DanhMuc().open_TrangChu(); //pass
//		fromTrangChu.open_DanhMuc().open_Hotline(); //pass
//		fromTrangChu.open_DanhMuc().open_HuongDan(); //pass
//		fromTrangChu.open_DanhMuc().open_NoiQuy(); //pass
//		fromTrangChu.open_DanhMuc().open_ThietLap(); //pass
//		fromTrangChu.open_DanhMuc().open_DangNhap(); //pass
		
		fromTrangChu.open_DanhMuc().open_ThietLap().turnOff_Offline().turnOn_Offline().turnOff_Offline().turnOff_Offline().turnOn_Offline().turnOn_Offline();
	}

}
