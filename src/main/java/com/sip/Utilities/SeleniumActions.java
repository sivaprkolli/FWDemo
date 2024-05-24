package com.sip.Utilities;

import com.aventstack.extentreports.Status;
import com.sip.ReportManager.ExtentReportManager;
import org.openqa.selenium.*;

public class SeleniumActions extends ExtentReportManager {

    private WebDriver driver;
    JavascriptExecutor jse;
    SeleniumWaits seleniumWaits;

    public SeleniumActions(WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        seleniumWaits = new SeleniumWaits(driver);
    }

    public void waitAndClickOnElement(WebElement element){
        try {
            seleniumWaits.waitForElementClickable(element).click();
            test.log(Status.PASS, "Successfully clicked on element");
        }catch (Exception e){
            test.log(Status.FAIL, "Unable to click on element");
        }
    }

    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public void clickOnElement(WebElement element) {
        try {
            element.click();
            test.log(Status.PASS, "Successfully clicked on element");
        } catch (ElementClickInterceptedException nse) {
            jse.executeScript("return arguments[0].click()", element);
            test.log(Status.PASS, "Successfully clicked on element");
        } catch (StaleElementReferenceException ste) {
            driver.findElement(By.xpath(""));
        }
    }

    public void typeValue(WebElement element, String data) {
        try {
            element.sendKeys(data);
            test.log(Status.PASS, "Successfully entered value in inputbox");
        } catch (NoSuchElementException e) {
            test.log(Status.FAIL, "Unable to type on element");
            throw new NoSuchElementException("Element not found");
        }
    }

    public String getTextOnElement(WebElement element) {

        return element.getText();
    }
}
