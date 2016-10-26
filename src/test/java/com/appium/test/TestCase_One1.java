package com.appium.test;

import org.testng.annotations.*;
import libraries.utility.*;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One1 extends TestBase {

	@BeforeSuite
	public void setUp() {
		config();
	}

	@AfterSuite
	public void tearDown() {
		cleanAll();
	}

	@AfterClass
	public void closeDriver() {
		closeApp();
	}

	@BeforeMethod
	public void testApp() {
		resetApp();
		Log.info("-----  Functional testing  -----");
	}

	@Test(groups = "function", dataProvider = "forSearch", dataProviderClass = DataProviderSource.class)
	public void testCase1(String search) throws Exception {

		// Test-step:
		fromTrangChu.tapOn_Search_buton().input_SearchText(search).submit_Search();
		
		ActualResult = fromSearchResult.get_Result();
		ExpectedResult = "Không có dữ liệu";

		assertEqual(ActualResult, ExpectedResult);

	}

}
