package test.java;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class CreateRecord {
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
    @Test(priority = 5)
    void createRecord() throws InterruptedException, IOException {

        String month ="August";
        String year ="2023";
        String day ="22";

        //login
        driver.manage().window().maximize();
        driver.get("http://13.73.224.244:5005/");
        Thread.sleep(3000);
        System.out.println("Kindly input your login credentials");
        driver.findElement(By.id("email")).sendKeys(getTestData("Login", 0, 2));
        Thread.sleep(Long.parseLong("1500"));
        driver.findElement(By.id("password")).sendKeys(getTestData("Login", 1, 2));
        Thread.sleep(Long.parseLong("1500"));
        WebElement enter = driver.findElement(By.xpath("//button[@class='p-element login-button login-button-disabled p-button p-component']"));
        enter.click();
        Thread.sleep(Long.parseLong("3000"));

        //Record
        driver.findElement(By.xpath("//span[text()='Record']")).click();
        Thread.sleep(Long.parseLong("1000"));
        driver.findElement(By.xpath("//button[@class='p-ripple p-element p-button-outlined p-button-danger p-button p-component']")).click();
        Thread.sleep(Long.parseLong("2000"));
        driver.findElement(By.xpath("//input[@class='input-image-label']")).sendKeys("C:\\Users\\USER\\Downloads\\Song schedule.pdf");
        Thread.sleep(Long.parseLong("3000"));
        driver.findElement(By.xpath("/html/body/app-root/app-site-layout/app-body/div/app-record/section/div/section/div[2]/div[2]/p-button/button")).click();
        Thread.sleep(Long.parseLong("5000"));

        //Vendor name
        driver.findElement(By.name("vendor")).sendKeys(getTestData("record", 0, 1));
        Thread.sleep(1000);
        //Invoice id
        driver.findElement(By.name("invoiceId")).sendKeys(getTestData("record", 1, 1));
        Thread.sleep(1000);
        //invoice title
        driver.findElement(By.name("title")).sendKeys(getTestData("record", 2, 1));
        Thread.sleep(1000);
        driver.findElement(By.name("description")).sendKeys(getTestData("record", 3, 1));
        Thread.sleep(1000);
        //category
        driver.findElement(By.xpath("//div[@class='p-dropdown p-component']")).click();
        driver.findElement(By.xpath("//span[text()='Invoice']")).click();
        Thread.sleep(Long.parseLong("1000"));

        //invoice date - Date picker
        driver.findElement(By.name("invoiceDate")).click();
        /*while (true) {
            String text = driver.findElement(By.xpath("//button[@class='p-datepicker-month p-link ng-tns-c58-19 ng-star-inserted']")).getText();
                if(text.equals(year) || text.equals(month)){
                    break;
                }
                else
                {
                    driver.findElement(By.xpath("//button[@class='p-ripple p-element p-datepicker-next p-link ng-tns-c58-5']")).click();
                }

        }*/
        driver.findElement(By.xpath("//span[text()='5']")).click();

        //Add an item
        driver.findElement(By.xpath("//p[text()='Add An Item']")).click();
        driver.findElement(By.xpath("(//input[@name='description'])[2]")).sendKeys(getTestData("record", 6, 1));
        driver.findElement(By.name("quantity")).sendKeys(getTestData("record", 4, 1));
        driver.findElement(By.name("price")).sendKeys(getTestData("record", 5, 1));
        driver.findElement(By.xpath("//input[@class='p-inputtext p-component p-element p-inputtext-sm ng-untouched ng-pristine']")).click();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement submit = driver.findElement(By.xpath("//button[@class='p-element create-record-btn p-button p-component']"));
        js.executeScript("arguments[0].scrollIntoView();",submit);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='p-element create-record-btn p-button p-component']"))));

        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='p-element create-record-btn p-button p-component']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='p-ripple p-element p-button-warning confirmation-button p-button p-component']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@class='p-element send-button p-button p-component']")).click();
        Thread.sleep(3000);
        driver.quit();

    }
}
