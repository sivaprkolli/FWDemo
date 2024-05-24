package com.sip.Utilities;

import org.openqa.selenium.*;

public class SeleniumSubActions extends SeleniumActions {

    private WebDriver driver;
    JavascriptExecutor jse;
    SeleniumWaits seleniumWaits;

    public SeleniumSubActions(WebDriver driver) {
        super(driver);
        this.driver = driver;
        jse = (JavascriptExecutor) driver;
        seleniumWaits = new SeleniumWaits(driver);
    }

    @Override
    public void clickOnElement(By by) {
        try {
            driver.findElement(by).click();
        }catch (NoSuchElementException e){
            e.printStackTrace();
            System.out.println(e.toString());
            throw new NoSuchElementException("No element found");
        }
    }

}

