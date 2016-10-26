package com.appium.test;

import org.testng.annotations.*;

import libraries.utility.Log;

/**
 * Created by User on 07/30/2016.
 */
public class TestCase_One extends TestBase {

    @BeforeClass
    public void setUp() {
        config();
        Log.info("-----  Begin TestCase_One  -----");
    }

    @AfterClass
    public void tearDown() {
        clean();
        Log.info("-----  End TestCase_One  -----");
    }

    @AfterMethod
    public void testClean() {
        Log.info("-----  End Method  -----");
    }

    @BeforeMethod
    public void testBed() {
        resetApp();
        Log.info("-----  Begin method  -----");
    }

    @Test(groups = "function", description = "Dang Tin", dataProvider = "tabbar")
    public void testCase(String tabbar) throws Exception {

        // Select tab
        fromTrangChu.tapOn_TabBar(tabbar);
    }

    @DataProvider(name = "tabbar")
    public static Object[][] forSearch() {
        return new Object[][]{
                {"danh muc"},
                {"Các mục khác"},
                {"bất động sản"},
                {"máy tính"}};
    }
}

