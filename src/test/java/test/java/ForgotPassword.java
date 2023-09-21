package test.java;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ForgotPassword extends BaseClass {
    @Test(priority = 12)
    void forgotPassword() throws InterruptedException {
        System.out.println("Kindly input your email address");
        driver.findElement(By.xpath("//a[@class='forgot-password-link']")).click();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ayoola.a.anibaba@dev.pwc.com");
        Thread.sleep(Long.parseLong("2000"));
        driver.quit();
    }
}
