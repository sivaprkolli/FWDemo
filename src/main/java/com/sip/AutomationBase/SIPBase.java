package com.sip.AutomationBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sip.ReportManager.ExtentReportManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

import static com.sip.ReportManager.ExtentReportManager.*;

public class SIPBase {
    protected WebDriver driver;
    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    /**
     *Initializing reports and time stamps, DB connections
     */
    @BeforeSuite
    public void initializeReports() throws MalformedURLException {
        generateReport();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.setExperimentalOption("excludeSwitches",  Collections.singletonList("disable-popup-blocking"));
        chromeOptions.merge(capabilities);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        //driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /**
     * Kill Session after the executions
     */
    @AfterSuite
    public void afterSuite() throws IOException, InterruptedException {
        driver.quit();
        closeReport();
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

    public void getScreenshot(Method method) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceImage = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationImage = new File(System.getProperty("user.dir")+"/screenshots/" + method.getName() +".png");
        FileUtils.copyFile(sourceImage, destinationImage);
    }

    public void reportTest(Method method) {
            test = extent.createTest(this.getClass().getSimpleName() + "::" + method.getName());
    }

    @BeforeMethod
    public void addReport(Method method){
        reportTest(method);
    }

    @AfterMethod
    public void takeScreenshotIfTestFails(ITestResult iTestResult, Method method) throws IOException {
        if(ITestResult.FAILURE == iTestResult.getStatus()){
            getScreenshot(method);
            test.log(Status.FAIL, iTestResult.getThrowable());

            test.fail("details",
                    MediaEntityBuilder.createScreenCaptureFromBase64String("Test").build());

            Allure.addAttachment(method.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }

    }

    public void addThumbnailToScreenshot(String filePath) throws IOException {
        File file = new File(filePath);
        Document report = Jsoup.parse(file, "UTF-8");
        Elements elements = report.select("a[data-featherlight='image']");
        for (Element element : elements) {
            if (element.attr("href").startsWith("data:image/png;base64")) {
                String thumbnail = String.format("<img class='r-img' src='%s'>", element.attr("href"));
                element.select("span").remove();
                element.append(thumbnail);
            }
        }
        BufferedWriter htmlWriter =
                new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
        htmlWriter.write(report.outerHtml());
        htmlWriter.close();
    }

}
