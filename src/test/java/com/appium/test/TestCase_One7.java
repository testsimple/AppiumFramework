package com.appium.test;

import org.testng.annotations.*;

import libraries.utility.Log;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One7 extends TestBase {

	@BeforeSuite
	public void setUp() {
		config();
	}

	@AfterSuite
	public void tearDown() {
		clean();
	}

	@BeforeMethod
	public void testBed() {
		resetApp();
		Log.info("-----  Functional testing  -----");
	}

	@Test(groups = "function", description = "Dang Tin", priority = 0)
	public void testCase2() throws Exception {
		// Test-step:
		// 1. Đăng nhập
		fromTrangChu.open_DanhMuc().open_DangNhap()
								   .input_Username("xxx-account-xxx")
								   .input_Password("xxx-123-xxx")
								   .tapOn_DangNhap();
		
		String inputValue = "Excel aksjfhdkjasf ksafjsahfksjfhksjhfksdjf ksj fsajkfajkfad";
		
		// 2. Đăng tin
		String title = TestBase.getTieuDe();
		fromTrangChu.open_DangTin().input_TieuDe(title)
								   .select_DanhMuc()
								   .select_MucChinh("các mục khác")
								   .select_MucPhu("rao vặt")
								   .send_Descript(inputValue)
								   .tapOn_DangTin()
								   .get_ResultChiTietTin();
		
		fromConfirmDangTin.get_ResultTitle();
		fromConfirmDangTin.get_AccountName();

		// 3. Verify
		ActualResult = fromConfirmDangTin.get_ResultChiTietTin();
		ExpectedResult = TestBase.getExpectedResult();

		assertEqual(ActualResult, ExpectedResult);
	}
	}
	
