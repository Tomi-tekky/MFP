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
import java.util.List;

public class CreateClient {
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
    @Test(priority = 1)
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
        Thread.sleep(Long.parseLong("1000"));
        driver.findElement(By.id("password")).sendKeys(getTestData("Login", 1, 1));
        Thread.sleep(Long.parseLong("1000"));
        WebElement enter = driver.findElement(By.xpath("//button[@class='p-element login-button login-button-disabled p-button p-component']"));
        enter.click();
        Thread.sleep(Long.parseLong("3000"));
    }

    @Test(priority = 3)
    void createClients() throws InterruptedException, IOException {
        driver.findElement(By.xpath("//span[text()='Clients']")).click();
        Thread.sleep(Long.parseLong("3000"));
        //Add user
        System.out.println("Add a New user");
        driver.findElement(By.xpath("//button[@class='p-element action-button p-button p-component']")).click();
        //Enter Firstname
        WebElement name=driver.findElement(By.id("name"));
        name.sendKeys(getTestData("Client",0,6));
        Thread.sleep(Long.parseLong("1000"));
        //Enter EmailAddress
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(getTestData("Client",1,6));

            /*for (int i=1; i<50; i++){
                email.sendKeys(getTestData("Client",1,1)+"@mailinator.com"+" ");

                }*/

        Thread.sleep(Long.parseLong("1000"));
        //Enter Address
        WebElement address=driver.findElement(By.name("address"));
        address.sendKeys(getTestData("Client",2,1));
        Thread.sleep(Long.parseLong("1000"));

        //Contact Person
        WebElement contactPerson=driver.findElement(By.name("contactPerson"));
        contactPerson.sendKeys(getTestData("Client",3,1));
        Thread.sleep(Long.parseLong("1000"));

        //Contact Email
        WebElement contactEmail= driver.findElement(By.id("contactMail"));
        contactEmail.sendKeys(getTestData("Client", 4, 1));
        Thread.sleep(Long.parseLong("1000"));

        //Contact PhoneNumber
        WebElement phoneNumber=driver.findElement(By.name("contactPhoneNumber"));
        phoneNumber.sendKeys(getTestData("Client",5,1));
        Thread.sleep(Long.parseLong("1000"));

        //Select Permissions
        driver.findElement(By.xpath("//div[@class='p-multiselect-trigger']")).click();
        List<WebElement> permissions = driver.findElements(By.xpath("//div[@class='p-checkbox-box']"));
        for (WebElement element : permissions) {
            String attrib = element.getAttribute("aria-checked");
            System.out.println(attrib);
            if (attrib.contentEquals("false")) {
                element.click();
                break;
            }
        }
        //Click create
        driver.findElement(By.xpath("//button[@class='p-element send-button p-button p-component']")).click();
        //Click oK
        Thread.sleep(Long.parseLong("3000"));
        driver.findElement(By.xpath("//button[@class='p-element send-button p-button p-component']")).click();
        System.out.println("User created successfully");
        Thread.sleep(Long.parseLong("3000"));
        driver.quit();
    }
}