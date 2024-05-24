package com.sip.Tests;

import com.aventstack.extentreports.Status;
import com.sip.AutomationBase.SIPBase;
import com.sip.ObjectRepository.AllProductsPage;
import com.sip.ObjectRepository.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends SIPBase {
    LoginPage loginPage;
    AllProductsPage allProductsPage;

    @BeforeClass
    public void initializePages(){
        loginPage = new LoginPage(driver);
        allProductsPage = new AllProductsPage(driver);
    }

    /**
     * verify successful login
     * @param un - username
     * @param pwd - password
     */
    @Parameters({"username","password"})
    @Test
    public void verifySuccessfulLogin(String un, String pwd){
        test.log(Status.INFO, "verifySuccessfulLogin started");
        loginPage.loginAsAdmin(un, pwd);
        String actualProductsHeading = allProductsPage.getProductsHeading();
        Assert.assertEquals(actualProductsHeading, "Product");
        test.log(Status.INFO, "verifySuccessfulLogin ended");
    }

    @Test
    public void verifyUserCreated(){
        test.log(Status.INFO, "verifyUserCreated started");
        Assert.assertTrue(true);
        test.log(Status.INFO, "verifyUserCreated ended");
    }

    @Test
    public void verifyUserEdited(){
        test.log(Status.INFO, "verifyUserEdited started");
        Assert.assertTrue(false);
        test.log(Status.INFO, "verifyUserEdited ended");
    }
}
