package com.appium.test;

import libraries.utility.Log;

import org.testng.annotations.*;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One9 extends TestBase {

	@BeforeClass
	public void setUp() {
		config();
		login();
	}

	@AfterClass
	public void tearDown() {
		clean();
		Log.info("-----  End TestCase_One8 Class  -----");
	}

	@BeforeMethod
	public void testBed() {
		Log.info("-----  Functional testing  -----");
	}

	@AfterMethod
	public void testClean() {
		closeApp();
		launchApp();
		Log.info("-----  End Method  -----");
	}

	@Test(groups = "function", description = "Dang Tin" , dataProvider = "dataset")
	public void testCase(String tieude, String danhmucchinhs, String danhmucphus) throws Exception {
		
		// Đăng tin
//		String title = TestBase.getTieuDe();
		
		String title = tieude;
		String danhmucchinh = danhmucchinhs;
		String danhmucphu = danhmucphus;

		
		String description = "Excel aksjfhdkjasf ksafjsahfksjfhksjhfksdjf ksj fsajkfajkfad";
		fromTrangChu.open_DangTin().input_TieuDe(title)
								   .select_DanhMuc()
								   .select_MucChinh(danhmucchinh)
								   .select_MucPhu(danhmucphu)
								   .select_Image()
								   .tapOn_ThuVien()
								   .tapOn_ImageThumbnail(1)
								   .tapOn_Huy()
								   .send_Descript(description)
								   .tapOn_DangTin()
								   .get_ResultChiTietTin();
		
		fromConfirmDangTin.get_ResultTitle();
		fromConfirmDangTin.get_AccountName();

		// Verify
		ActualResult = fromConfirmDangTin.get_ResultChiTietTin();
		ExpectedResult = TestBase.getExpectedResult();

		assertEqual(ActualResult, ExpectedResult);
	}

	@DataProvider(name = "dataset")
	public static Object[][] forSearch() {
		return new Object[][]{
				{"tieu de 1", "các mục khác", "mẹ và bé"},
				{"tieu de 2", "các mục khác", "du học"}};
	}
}
	
