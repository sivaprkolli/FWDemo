package com.sip.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#user-name")
    private WebElement userNameInputField;

    @FindBy(css = "#password")
    private WebElement passwordInputField;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    /**
     * enter un and pwd and do login
     * @param username - un
     * @param password - pwd
     */
    public void loginAsAdmin(String username, String password) {
        userNameInputField.sendKeys(username);
        passwordInputField.sendKeys(password);
        loginButton.click();
    }

}
