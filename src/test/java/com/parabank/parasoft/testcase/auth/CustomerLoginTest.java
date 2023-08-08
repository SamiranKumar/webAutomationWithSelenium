package com.parabank.parasoft.testcase.auth;

import com.parabank.parasoft.pages.auth.CustomerLoginPage;
import com.parabank.parasoft.pages.home.HomePage;
import com.parabank.parasoft.testcase.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CustomerLoginTest extends BaseTest {


    @Test
    public void loginWithoutAnyCredential() {
        CustomerLoginPage loginPage = page.getInstance(CustomerLoginPage.class);
        loginPage.doLogin();

        Assert.assertTrue(loginPage.hasError());
    }

    @Test
    public void loginWithCredential() {
        HomePage homePage = page.getInstance(CustomerLoginPage.class)
                        .doLogin(getUserName(), getPassword());

        Assert.assertTrue(homePage.hasLogoutLink());
    }
}
