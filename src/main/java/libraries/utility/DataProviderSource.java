package libraries.utility;

import org.testng.annotations.DataProvider;

public class DataProviderSource {

    private static String path = System.getProperty("user.dir") + "/src/test/java/resources/Dataset1.xlsx";
    private static String[] header = {"name", "address", "email", "postcode"};
    public static Object[][] data;

    @DataProvider(name = "excelData")
    public static Object[][] excelData() {
        try {
            ExcelUtils.openByPath(path);
            String sheetname = "Dataset";
            ExcelUtils.getSheet(sheetname);

            // GetRowNum code + 1
            int secondRow = ExcelUtils.getRowNumber(sheetname) - 1;

            int cols = ExcelUtils.getColNumberOfFirstRow();

            data = new Object[secondRow][cols];
            for (int i = 0; i < secondRow; i++) {
                data[i][0] = ExcelUtils.getCellValue((i + 2), header[0]);
                data[i][1] = ExcelUtils.getCellValue((i + 2), header[1]);
                data[i][2] = ExcelUtils.getCellValue((i + 2), header[2]);
                data[i][3] = ExcelUtils.getCellValue((i + 2), header[3]);
            }
        } catch (Exception e) {
            System.out.println("- Error --- Have problem in Dataprovider");
        }
        return data;
    }
    
    @DataProvider(name = "forSearch")
    public static Object[][] forSearch() {
    	return new Object[][]{{"Hello em iu"},{"Chao em iu"}};
    }
}
