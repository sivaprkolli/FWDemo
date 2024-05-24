package com.sip.ObjectRepository;

import com.sip.Utilities.SeleniumActions;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;
    SeleniumActions seleniumActions;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumActions = new SeleniumActions(driver);
    }

    @FindBy(css = "#user-name")
    private WebElement userNameInputField;

    @FindBy(css = "#password")
    private WebElement passwordInputField;

    @FindBy(css = "#login-button")
    private WebElement loginButton;


    public WebElement getLoginButton(){
        return loginButton;
    }

    /**
     * enter un and pwd and do login
     *
     * @param username - un
     * @param password - pwd
     */
    public void loginAsAdmin(String username, String password) {
        seleniumActions.typeValue(userNameInputField, username);
        seleniumActions.typeValue(passwordInputField, password);

        try {
            seleniumActions.waitAndClickOnElement(loginButton);
        } catch (StaleElementReferenceException ste) {
            driver.findElement(By.xpath(""));
        }
    }

}
