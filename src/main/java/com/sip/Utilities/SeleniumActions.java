package com.sip.Utilities;

import org.openqa.selenium.*;

public class SeleniumActions {

    private WebDriver driver;
    JavascriptExecutor jse;
    SeleniumWaits seleniumWaits;

    public SeleniumActions(WebDriver driver) {
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        seleniumWaits = new SeleniumWaits(driver);
    }

    public void waitAndClickOnElement(WebElement element){
        seleniumWaits.waitForElementClickable(element).click();
    }

    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    public void clickOnElement(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException nse) {
            jse.executeScript("return arguments[0].click()", element);
        } catch (StaleElementReferenceException ste) {
            driver.findElement(By.xpath(""));
        }
    }

    public void typeValue(WebElement element, String data) {
        try {
            element.sendKeys(data);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element not found");
        }
    }

    public String getTextOnElement(WebElement element) {
        return element.getText();
    }
}
