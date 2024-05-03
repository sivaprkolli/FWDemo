package com.sip.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class SeleniumWaits {

    FluentWait fluentWait;

    public SeleniumWaits(WebDriver driver){
        fluentWait = new FluentWait(driver)
                .withMessage("element not found")
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2));
    }

    public WebElement waitForElementClickable(WebElement element){
       return (WebElement) fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementClickable(By by){
        fluentWait.until(ExpectedConditions.elementToBeClickable(by));
    }


}
