package com.sip.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllProductsPage {

    public AllProductsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".title")
    private WebElement productsHeading;


    /**
     * get products heading
     * @return string
     */
    public String getProductsHeading(){
        return productsHeading.getText();
    }

}
