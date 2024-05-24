package com.sip.ReportManager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sip.AutomationBase.SIPBase;
import org.openqa.selenium.Platform;

import java.lang.reflect.Method;

public class ExtentReportManager extends SIPBase {


    public static void generateReport(){
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/extentReport/report.html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "SHIVAS");
        extent.setSystemInfo("Environment", "MAC");
        extent.setSystemInfo("User Name", "Shiva");
        extent.setSystemInfo("OS", Platform.MAC.name());
        extent.setSystemInfo("Browser", "Chrome");

        htmlReporter.config().setDocumentTitle("SIP Automation Report");
        htmlReporter.config().setReportName("SIP Smoke Report");
        htmlReporter.config().setTheme(Theme.DARK);
        //htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setTimelineEnabled(true);
        htmlReporter.config().thumbnailForBase64();
    }

    public static void createReport(Method method){
        extent.createTest(test.getClass().getName() + " :: " + method.getName());
    }

    public static void closeReport(){
        extent.flush();
    }
}
