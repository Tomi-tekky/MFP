package test.java;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class RequestRecord {
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

        driver.getCurrentUrl();
        System.out.println("Kindly input your login credentials");
        driver.findElement(By.id("email")).sendKeys(getTestData("Login", 0, 1));
        Thread.sleep(Long.parseLong("1500"));
        driver.findElement(By.id("password")).sendKeys(getTestData("Login", 1, 1));
        Thread.sleep(Long.parseLong("1500"));
        WebElement enter = driver.findElement(By.xpath("//button[@class='p-element login-button login-button-disabled p-button p-component']"));
        enter.click();
        Thread.sleep(Long.parseLong("3000"));
    }

    @Test(priority = 11)
    void requestRecord() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Record']")).click();

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[@class='p-button-icon p-button-icon-right pi pi-chevron-down']")))).click();
       driver.findElement(By.xpath("(//button[text()=' Request New '])")).click();
       Thread.sleep(Long.parseLong("3000"));

       //Company
       driver.findElement(By.xpath("//span[text()='Select Company']")).click();
       driver.findElement(By.xpath("//li[@class='p-ripple p-element p-dropdown-item']")).click();
       Thread.sleep(Long.parseLong("1000"));

        //Request Type
        driver.findElement(By.xpath("(//div[@class='p-dropdown-trigger'])[2]")).click();
        // driver.findElement(By.xpath("//span[@class='p-dropdown-label p-inputtext p-placeholder ng-star-inserted']")).click();
        driver.findElement(By.xpath("//span[text()='Supporting Document']")).click();
        Thread.sleep(Long.parseLong("1000"));

       //Email Address
       driver.findElement(By.xpath("//div[@class='p-multiselect-label p-placeholder']")).click();
       driver.findElement(By.xpath("//div[@class='p-checkbox-box']")).click();
       Thread.sleep(Long.parseLong("2000"));

       //Request Description

        driver.findElement(By.name("description")).sendKeys("ok");
        driver.findElement(By.xpath("//button[@class='p-element send-button p-button p-component']")).click();
        Thread.sleep(Long.parseLong("2000"));
        driver.findElement(By.xpath("//button[@class='p-element send-button p-button p-component']")).click();
        Thread.sleep(Long.parseLong("5000"));
       driver.quit();
    }

}
