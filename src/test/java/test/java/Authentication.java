package test.java;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;



public class Authentication {

    public WebDriver driver;

    @BeforeClass
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "C://BrowserDriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("disable-infobars");
        driver = new ChromeDriver(options);
    }
    public static String getTestData(String sheetName, int cID, int rID) throws InterruptedException, IOException {
        File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\ValidTestDataMFP5.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet currentSheet = wb.getSheet(sheetName);
        Row cellRows = currentSheet.getRow(rID);
        Cell cellValue = cellRows.getCell(cID);
        return cellValue.getStringCellValue();
    }

    @Test()
    void login() throws InterruptedException, IOException {

           driver.manage().window().maximize();
            driver.get("http://13.73.224.244:5005/");
            String url = driver.getCurrentUrl();
            Assert.assertEquals("http://13.73.224.244:5005/", url);
            Assert.assertTrue(true);
            Thread.sleep(3000);
            String HomePage = driver.getTitle();
            System.out.println(HomePage);

        //for (int k=1; k<=60; k++) {

            driver.getCurrentUrl();
            System.out.println("Kindly input your login credentials");
            driver.findElement(By.id("email")).sendKeys(getTestData("Login", 0, 1));
            Thread.sleep(Long.parseLong("1500"));
            driver.findElement(By.id("password")).sendKeys(getTestData("Login", 1, 1));
            Thread.sleep(Long.parseLong("1500"));
            WebElement enter = driver.findElement(By.xpath("//button[@class='p-element login-button login-button-disabled p-button p-component']"));
            enter.click();
            Thread.sleep(Long.parseLong("3000"));


            String page = "MFPFrontend";
            if (HomePage.equals(page)) {
                System.out.println("Welcome to MFP dashboard");
            } else if (HomePage.equals(url)) {
                //String ActualError = driver.findElement(By.xpath(".//*[@class='error-message']")).getText();
                String ActualError = driver.findElement(By.xpath(".//*[@class='error-message']")).getAttribute("innerHTML");
                String ExpectedError = "User not found";
                System.out.println(ActualError);
                Assert.assertEquals(ActualError, ExpectedError);
                Assert.assertTrue(ActualError.contains("User not found"));
                Thread.sleep(Long.parseLong("3000"));

            }
            driver.quit();
        }
}
