package com.appium.test;

import org.testng.annotations.*;

import libraries.utility.Log;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One6 extends TestBase {

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

	@Test(groups = "function", description = "Dang tin")
	public void testCase2() throws Exception {
		// Test-step:
//		System.out.println(driver.getPageSource());
		// Đăng nhập
		fromTrangChu.open_DanhMuc().open_DangNhap()
								   .input_Username("xxx-account-xxx")
								   .input_Password("xxx-123-xxx")
								   .tapOn_DangNhap();
		
		String inputValue = "test thu aksjfhdkjasf ksafjsahfksjfhksjhfksdjf ksj fsajkfajkfad"
   							+ "ksajdfhkshdfkjahk kfsf sa fksfd aksdf skadfj fksad fkd sk sdsdf"
   							+ "kdsaflasdkflsadjf dsfsa sadfasadfsadfsaf dsafsadfasdfsdfadfasl"
   							+ "sdfsadfsadfs sdfsdafsafsadfsadfsasadflsafllllllllllllllllllllll"
   							+ "--------------------------------------------------------------l";
		
		fromTrangChu.open_DangTin().input_TieuDe("Tiêu đề")
								   .select_DanhMuc()
								   .select_MucChinhOnly("chimcảnh")
								   .send_Descript(inputValue)
								   .tapOn_XemThu()
								   .get_Title();
		
		ActualResult = fromConfirmDangTin.getWebTextThu();
		Log.info(ActualResult);
		assertEqual(ActualResult, inputValue);
		
//		fromTrangChu.open_DangTin().input_TieuDe("Tiêu đề")
//								   .select_DanhMuc()
//								   .select_MucChinhOnly("chimcảnh")
//								   .send_Descript("test thu")
//								   .tapOn_DangTin();
	}
}
