package com.sip.AutomationBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class SIPBase {
    protected WebDriver driver;

    /**
     *Initializing reports and time stamps, DB connections
     */
    @BeforeSuite
    public void initializeReports(){
        driver = new ChromeDriver();
    }

    /**
     * Kill Session after the executions
     */
    @AfterSuite
    public void afterSuite(){
        driver.quit();
    }

    /**
     * Launching the application
     * @param appUrl - Application url
     */
    @Parameters("url")
    @BeforeTest
    public void beforeTest(String appUrl){
        driver.get(appUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}
