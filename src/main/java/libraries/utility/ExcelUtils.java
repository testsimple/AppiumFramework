package libraries.utility;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelUtils {
    private static WorkbookFactory workbookFactory;
    private static Workbook workbook;
    private static Sheet sheet;
    private static Row row;
    private static Cell cell;
    private static File file;
    private static String pathfile;
    private static String pathroot = "src/test/java/resources/";
    private static String extFile = ".";
    private static FileInputStream fIS;
    private static FileOutputStream fOS;

    // 1.Open
    @SuppressWarnings("static-access")
	public static void open(String filename) {
        pathfile = pathroot + filename;
        file = new File(pathfile);

        if (file.exists() && file.canRead()) {
            try {
                fIS = new FileInputStream(file);
                workbook = workbookFactory.create(fIS);
                System.out.println(">> Open file: " + filename);
            } catch (Exception e) {
                System.out.println(">> Can not open file: " + filename);
            } finally {
                if (fIS != null) {
                    try {
                        fIS.close();
                    } catch (IOException e) {
                        System.out.println(">> Can not close file: " + filename);
                    }
                }
            }
        } else {
            System.out.println(">> File does not exist. Please check again!!!");
        }
        Runtime.getRuntime().gc();
    }

    //---
    @SuppressWarnings("static-access")
	public static void openByPath(String path) {
        pathfile = path;
        file = new File(pathfile);
        String filename = file.getName();

        if (file.exists() && file.canRead()) {
            try {
                fIS = new FileInputStream(file);
                workbook = workbookFactory.create(fIS);
                System.out.println(">> Open file: " + filename);
            } catch (Exception e) {
                System.out.println("- Error --- Can not open file: " + filename);
            } finally {
                if (fIS != null) {
                    try {
                        fIS.close();
                    } catch (IOException e) {
                        System.out.println("- Error --- Can not close file: " + filename);
                    }
                }
            }
        } else {
            System.out.println("- Error --- File does not exist. Please check again!!!");
        }
        Runtime.getRuntime().gc();
    }

    // 2.Save
    public static void save(){
        try {
            fOS = new FileOutputStream(file);
            workbook.write(fOS);
            fOS.flush();
            fOS.close();
            System.out.println(">> File saved successfully");
        }catch (Exception e){
            System.out.println("- Error --- Unable to save file. Please check again.");

        } finally {
            if (fOS != null) {
                try {
                    fOS.close();
                } catch (IOException e) {
                    System.out.println("- Error --- File stream can not close");
                }
            }
        }
    }

    // 3.SaveAs
    public static void saveAs(String NewName) throws IOException, InvalidFormatException {
        extFile += FilenameUtils.getExtension(pathfile);
        try {
            FileOutputStream fOS = new FileOutputStream(pathroot + NewName + extFile);
            try {
                workbook.write(fOS);
                fOS.flush();
                fOS.close();
                System.out.println(">> Data was save as new file: " + NewName + extFile);
            } finally {
                if (fOS != null) {
                    fOS.close();
                }
            }
        } catch (Exception e) {
            System.out.println("- Error --- Unable to save file. Please check again.");
        }
    }

    // 4.Sheet
    // 4.1 Check sheet is exist
    public static boolean isSheet(String SheetName) {
        return (workbook.getSheetIndex(SheetName) >= 0);
    }

    // 4.2 Add new sheet
    public static void createSheet(String SheetName) {
        if (!isSheet(SheetName)) {
            workbook.createSheet(SheetName);
        } else {
            System.out.println(">> Sheet is existed");
        }
    }

    // 4.3 Remove sheet by name
    public static void deleteSheet(String SheetName) {
        if (isSheet(SheetName)) {
            int sheetIndex = workbook.getSheetIndex(SheetName);
            workbook.removeSheetAt(sheetIndex);
            System.out.println(">> Sheet" + SheetName + " is deleted");
        } else {
            System.out.println(">> Do not exist sheet");
        }
    }

    // 4.4 Remove sheet by index
    public static void deleteSheet(int sheetindex) {
        int i = workbook.getNumberOfSheets();
        if (i >= sheetindex && sheetindex > -1) {
            workbook.removeSheetAt(sheetindex);
            System.out.println(">> Sheet" + sheetindex + "is deleted");
        } else {
            System.out.println(">> Sheet index is unavailable");
        }
    }

    // 5. Working with sheet
    public static Sheet getSheet(String SheetName) {
        if (!isSheet(SheetName)) {
            sheet = workbook.createSheet(SheetName);
            System.out.println(">> Sheet " + SheetName + " is created and ready");
        } else {
            sheet = workbook.getSheet(SheetName);
        }
        return sheet;
    }

    // 5.1 Column
    // 5.1.0 Add column primitive
    public static void addColumn(Sheet sheet, String ColName) {
        row = sheet.getRow(0);

        // Check null of first row
        if (row == null) {
            row = sheet.createRow(0);
        }

        // Check null of first cell
        if (row.getLastCellNum() == -1) {
            cell = row.createCell(0);
        } else {
            cell = row.createCell(row.getLastCellNum());
            sheet.autoSizeColumn(row.getLastCellNum());
        }

        cell.setCellValue(ColName);
        setStypeCell();
    }

    // 5.1.1 Add column enhance by string
    public static void addColumn(String SheetName, String ColValue) {
        sheet = getSheet(SheetName);
        addColumn(sheet, ColValue);
    }

    public static void addColumn(String ColValue) {
        addColumn(sheet, ColValue);
    }

    // 5.1.2 Convert Column name to Index
    public static final int convertColNametoIndex(Sheet sheet, String colValue) {
        int fromRow = 1; //From row 0
        return convertColNametoIndex(colValue, fromRow);
    }

    public static int convertColNametoIndex(String colValue) {
        return convertColNametoIndex(sheet, colValue);
    }

    public static int convertColNametoIndex(String colValue, int fromRow) {
        try {
            row = sheet.getRow(fromRow - 1);
            int indexCol = -1;
            int lstCell = row.getLastCellNum();
            for (int i = 0; i < lstCell; i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colValue)) {
                    indexCol = i;
                }
            }
            return indexCol;
        } catch (Exception e) {
            System.out.println("- Error --- Have problem on finding col-name");
        }
        return -1;
    }

    public static final int convertColNametoIndexWithBlackBefore(String colValue, int fromRow) {
        row = sheet.getRow(fromRow - 1);
        int indexCol = -1;
        try {
            Iterator<?> celItr = row.cellIterator();
            while (celItr.hasNext()) {
                cell = (Cell) celItr.next();
                if (cell.toString().equals(colValue)) {
                    indexCol = cell.getColumnIndex();
                }
            }
            return indexCol;
        } catch (Exception e) {
            System.out.println("- Error --- Have problem on finding col-name");
        }
        return indexCol;
    }


    // 5.2.Cell
    // 5.2.0 Set cell value primitive
    public static void setCell(Sheet sheet, int rowIndex, int colIndex, String value) {
        try {
            row = getRow(sheet, rowIndex);
            cell = getCell(row, colIndex);
            cell.setCellValue(value);
        } catch (Exception e) {
            System.out.println("- Error --- Cell is invalid");
        }
    }

    //---Modify setCell primitive
    private static Row getRow(Sheet sheet, int indexRow) {
        row = sheet.getRow(indexRow - 1);
        if (row == null) {
            row = sheet.createRow(indexRow - 1);
        }
        return row;
    }

    private static Cell getCell(Row row, int indexCol) {
        cell = row.getCell(indexCol - 1);
        if (cell == null) {
            cell = row.createCell(indexCol - 1);
        }
        return cell;
    }

    // 5.2.1 Set cell value by stringsheet
    public static void setCell(String SheetName, int rowIndex, int colIndex, String value) {
        sheet = getSheet(SheetName);
        setCell(sheet, rowIndex, colIndex, value);
    }

    // 5.2.2
    public static void setCell(String SheetName, int rowIndex, String colName, String value) {
        sheet = getSheet(SheetName);
        int colIndex = convertColNametoIndex(sheet, colName) + 1;
        setCell(sheet, rowIndex, colIndex, value);
    }

    //5.2.3
    public static void setCell(int rowIndex, String colName, String value) {
        setCell(sheet.getSheetName(), rowIndex, colName, value);
    }

    // 5.2.4 Get value from cell with formula
    public static String getCellValue(String SheetName, int rowIndex, int colIndex) {

        row = getRow(sheet, rowIndex);
        cell = getCell(row, colIndex);
        FormulaEvaluator formula = workbook.getCreationHelper().createFormulaEvaluator();
        if (cell != null) {
            switch (formula.evaluateInCell(cell).getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    return cell.getStringCellValue();
                case Cell.CELL_TYPE_BLANK:
                    return "";
                case Cell.CELL_TYPE_BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case Cell.CELL_TYPE_NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case Cell.CELL_TYPE_FORMULA:
                    return String.valueOf(cell.getCellFormula());
                case Cell.CELL_TYPE_ERROR:
                    return String.valueOf(">> Formula Error " + cell.getErrorCellValue());
            }
        }
        return null;
    }


    // 5.2.5 Enhance getCellValue with colName
    public static String getCellValue(String SheetName, int rowIndex, String colName) {
        sheet = getSheet(SheetName);
        row = getRow(sheet, rowIndex);
        int colIndex = convertColNametoIndex(sheet, colName) + 1;
        cell = getCell(row, colIndex);
        return getCellValue(SheetName, rowIndex, colIndex);
    }

    // 5.2.6 Overload getCellValue
    public static String getCellValue(int rowIndex, String colName) {
        int colIndex = convertColNametoIndex(sheet, colName) + 1;
        if (colIndex < 1 || rowIndex < 1) {
            throw new IllegalArgumentException("- Error --- Value inputted is not eligible");
        }
        return getCellValue(sheet.getSheetName(), rowIndex, colIndex);
    }

    // 5.2.7 Overload getCellValue
    public static String getCellValue(int rowIndex, int colIndex) {
        if (colIndex < 1 || rowIndex < 1) {
            throw new IllegalArgumentException("- Error --- Value inputted is not eligible");
        }
        return getCellValue(sheet.getSheetName(), rowIndex, colIndex);
    }

    // 5.3 Get value of column
    // 5.3.0 getColumnValues by colIndex
    public static List<String> getColumnValues(String SheetName, int colIndex, int fromRow) {

        List<String> cellValues = new ArrayList<String>();
        String cellValue;
        try {
            int rowNums = getRowNumber();
            for (int i = fromRow; i <= rowNums; i++) {
                try {
                    cellValue = getCellValue(SheetName, i, colIndex);
                    cellValues.add(cellValue);
                } catch (Exception e) {
                    cellValues.add("");
                }
            }
        } catch (Exception e) {
            System.out.println("- Error --- SheetName, ColIndex or FromRowIndex is unavailable");
        }
        return cellValues;
    }

    // Create default getColumnValues from row 1
    public static List<String> getColumnValues(String SheetName, int colIndex) {
        int fromRow = 2;
        return getColumnValues(SheetName, colIndex, fromRow);
    }

    // 5.3.1 getColumnValues by colName
    public static List<String> getColumnValues(String SheetName, String colName) {
        sheet = getSheet(SheetName);
        int colIndex = convertColNametoIndex(sheet, colName) + 1;
        return getColumnValues(SheetName, colIndex);
    }

    // 5.3.2
    public static List<String> getColumnValues(int colIndex) {
        return getColumnValues(sheet.getSheetName(), colIndex);
    }

    // 5.3.3
    public static List<String> getColumnValues(String colName) {
        int colIndex = convertColNametoIndex(sheet, colName) + 1;
        return getColumnValues(sheet.getSheetName(), colIndex);
    }

    // 5.4 Get value of row
    public static List<String> getRowValue(String SheetName, int rowIndex, int fromCol) {
        int colNum = getColNumberAnyRow(rowIndex);

        List<String> cellValues = new ArrayList<>();
        String cellValue;
        try {
            for (int i = fromCol; i <= colNum; i++) {
                try {
                    cellValue = getCellValue(SheetName, rowIndex, i);
                    cellValues.add(cellValue);
                } catch (Exception e) {
                    cellValues.add("");
                }
            }
        } catch (Exception e) {
            System.out.println("- Error --- SheetName, Row or Col is unavailable");
        }
        return cellValues;
    }

    //5.4.1
    public static List<String> getRowValue(int rowIndex, int fromCol) {
        return getRowValue(sheet.getSheetName(), rowIndex, fromCol);
    }

    // 6. Cell Style
    public static void setStypeCell(Cell cell) {

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setShrinkToFit(true);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.YELLOW.getIndex());
        font.setBold(true);
        font.setItalic(false);
        cellStyle.setFont(font);

        sheet.autoSizeColumn(cell.getColumnIndex());
        cell.setCellStyle(cellStyle);
    }

    public static void setStypeCell() {
        setStypeCell(cell);
    }


    public static void setRowHeight(int rowIndex, int size) {
        try {
            sheet.getRow(rowIndex - 1).setHeightInPoints(size);
        } catch (Exception e) {
            System.out.println("- Error --- Row is unavailable");
        }
    }

    public static void setColWidth(int colIndex, int size) {
        sheet.setColumnWidth(colIndex - 1, size * 2048);
    }

    //---Methods Supporting
    public static String getPath() {
        System.out.println(pathfile);
        return pathfile;
    }

    //---
    public static int getRowNumber(String SheetName) {
        sheet = getSheet(SheetName);
        int rowNum = sheet.getLastRowNum();
        return rowNum + 1;
    }

    public static int getRowNumber() {
        int rowNum = sheet.getLastRowNum();
        return rowNum + 1;
    }

    //---
    public static int getColNumberOfFirstRow(String SheetName) {
        sheet = getSheet(SheetName);
        try {
            int colNum = sheet.getRow(0).getLastCellNum();
            return colNum;
        } catch (Exception e) {
            System.out.println(">> First row is empty");
            return -1;
        }
    }

    public static int getColNumberOfFirstRow() {
        return getColNumberOfFirstRow(sheet.getSheetName());
    }

    //---
    public static int getColNumberAnyRow(String SheetName, int rowIndex) {
        sheet = getSheet(SheetName);
        try {
            int colNum = sheet.getRow(rowIndex - 1).getLastCellNum();
            return colNum;
        } catch (Exception e) {
            System.out.println("- Error --- Do not exist Row: " + rowIndex);
            return -1;
        }
    }

    public static int getColNumberAnyRow(int rowIndex) {
        return getColNumberAnyRow(sheet.getSheetName(), rowIndex);
    }
}
