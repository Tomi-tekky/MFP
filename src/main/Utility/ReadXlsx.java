/*

package test.java.Utility;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ReadXlsx {
    //public static void main(String[] args) throws IOException {
        //ReadXlsx read = new ReadXlsx();
        //read.getData("Login");}

    @DataProvider(name="MfpData")
    public String[][] getData(Method m) throws IOException {

        String excelSheetName = m.getName();
        File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\ValidTestDataMFP.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet1 = wb.getSheet(excelSheetName);

        int totalRows = sheet1.getLastRowNum();
        System.out.println(totalRows);
        Row cellRows = sheet1.getRow(0);
        int totalCols = cellRows.getLastCellNum();
        System.out.println(totalCols);

        String[][] testData = new String[totalRows][totalCols];
        DataFormatter format = new DataFormatter();
        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                testData[i - 1][j] = format.formatCellValue(sheet1.getRow(i).getCell(j));
                System.out.println(testData[i - 1][j]);
            }
        }
        return testData;
    }
}
*/