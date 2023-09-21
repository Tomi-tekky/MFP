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

public class CreateClientUser {
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
    @Test(priority = 4)
    void createClientUser()throws InterruptedException, IOException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text()='Clients']")))).click();
        Thread.sleep(Long.parseLong("3000"));
        driver.findElement(By.xpath("//a[@href='/general-admin/client-details/CL204006']")).click();
        driver.findElement(By.xpath("//button[@icon='pi pi-plus']")).click();
        driver.findElement(By.xpath("//button[text()=' Add new Member ']")).click();
        Thread.sleep(2000);
        //First name
        driver.findElement(By.id("firstName")).sendKeys(getTestData("ClientUser", 0, 2));
        Thread.sleep(2000);
        //Lastname
        driver.findElement(By.id("lastName")).sendKeys(getTestData("ClientUser", 1, 2));
        Thread.sleep(2000);
        //Email address
        driver.findElement(By.id("email")).sendKeys(getTestData("ClientUser", 2, 2));
        Thread.sleep(2000);
        //Select Role
        driver.findElement(By.xpath("//span[text()='Select Role']")).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@class='p-ripple p-element p-dropdown-item']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class='p-element send-button p-button p-component']")).click();

    }
}
