package com.course.bvtcase.productmanagement;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.utils.ConfigFileH5;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class ApplyForProductH5 {
    public WebDriver driver;

    @BeforeTest
    public void beforeTest() {

            TestConfig.h5Loign = ConfigFileH5.getUrl(InterfaceName.H5LOGIN);
    }

    @AfterTest
    public void afterTest(){

    }
    @Test(groups = "applyForProductH5",dependsOnGroups="LoanApproverloginCase",description = "H5申请产品UI")
    public void applyForProductH5() {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.get(TestConfig.h5Loign);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.className("half-common-button")).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.className("password-input")).sendKeys("jrzhfw2019");
        driver.findElement(By.className("half-blue-button")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<WebElement> ButtonElement = driver.findElements(By.className("tabbar-text"));
        ButtonElement.get(3).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.id("username")).sendKeys("zjzwfwcsqy");
        driver.findElement(By.id("password")).sendKeys("zjzwfwcsqy123");
        driver.findElement(By.id("commitBtn")).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<WebElement> ButtonElement3 = driver.findElements(By.className("tabbar-text"));

        ButtonElement3.get(1).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<WebElement> ButtonElement1 = driver.findElements(By.className("dqProduct-box"));
        ButtonElement1.get(0).click();
        //driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div/div[1]/div[1]/div/div[1]/div")).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //driver.findElement(By.className("half-common-button")).click();
        driver.findElement(By.className("submit-button")).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<WebElement> ButtonElement2 = driver.findElements(By.className("input-content"));
        ButtonElement2.get(0).sendKeys("测试申请");
        ButtonElement2.get(1).sendKeys("13572562626");
        driver.findElement(By.id("placeholder")).click();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.findElement(By.className("cube-picker-confirm")).click();
        driver.findElement(By.className("input-content-long")).sendKeys("测试");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.id("placeholder")).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<WebElement> ButtonElement4 = driver.findElements(By.className("select-cell"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ButtonElement4.get(1).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[5]/span")).click();
        driver.findElement(By.className("submit-button")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.findElement(By.className("icon")).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        driver.close();
    }
}
