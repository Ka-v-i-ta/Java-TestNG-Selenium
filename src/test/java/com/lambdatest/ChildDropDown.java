package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGTodo1 {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = "";
        String authkey = "";
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "MicrosoftEdge");
        caps.setCapability("version", "105.0");
        caps.setCapability("build", "TestNG With Java");
        caps.setCapability("name", m.getName() + " - " + this.getClass().getName());
        caps.setCapability("plugin", "git-testng");



        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");

driver.get("https://onlinedegrees.purdue.edu/purdue-global/");
        Select Dropdown=  new Select(driver.findElement(By.xpath("//*[@id=\"aos\"]")));
        Dropdown.selectByVisibleText("Education");


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        Select Dropdown1 = new Select(driver.findElement(By.xpath("/html/body/main/form/section/div[2]/div/div/section[1]/section[1]/div[2]/select")));
        //  Select Dropdown1 = new Select(driver.findElement(By.xpath("//*[@id=\"degree\"]")));

        Dropdown1.selectByVisibleText("Associate's");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        Select Dropdown2 = new Select(driver.findElement(By.xpath("//*[@id=\"program\"]")));

        Dropdown2.selectByVisibleText("Early Childhood Development");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElement(By.id("btnContinue")).click();
        Thread.sleep(150);

        System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}

