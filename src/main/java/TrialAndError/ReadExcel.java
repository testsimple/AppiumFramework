package TrialAndError;


import libraries.utility.ExcelUtils;

public class ReadExcel {
	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "/src/test/java/testdata/input.xlsx";
		ExcelUtils.openByPath(path);
        String sheetname = "Description";
        ExcelUtils.getSheet(sheetname);
        
        String a = ExcelUtils.getCellValue(1, 1);
        System.out.println(a);
	}
}
