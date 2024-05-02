package com.sip.Tests;

import com.sip.AutomationBase.SIPBase;
import com.sip.Pages.AllProductsPage;
import com.sip.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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
        loginPage.loginAsAdmin(un, pwd);
        String actualProductsHeading = allProductsPage.getProductsHeading();
        Assert.assertEquals(actualProductsHeading, "Products");
    }
}
