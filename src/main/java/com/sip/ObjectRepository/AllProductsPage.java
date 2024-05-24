package com.sip.ObjectRepository;

import com.sip.Utilities.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllProductsPage {

    SeleniumActions seleniumActions;
    public AllProductsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
    }

    @FindBy(css = ".title")
    private WebElement productsHeading;


    /**
     * get products heading
     * @return string
     */
    public String getProductsHeading(){
        return seleniumActions.getTextOnElement(productsHeading);
    }

}
