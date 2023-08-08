package com.parabank.parasoft.pages.auth;

import com.parabank.parasoft.pages.base.BasePage;
import com.parabank.parasoft.pages.home.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage extends BasePage {

    private final By userNameSelector = By.name("username");
    private final By passwordSelector = By.name("password");
    private final By loginButtonSelector = By.cssSelector("input.button");
    private final By errorMessageSelector = By.className("error");

    public CustomerLoginPage(WebDriver webDriver) {
        super(webDriver);
    }


    public CustomerLoginPage fillUserName(String userName) {
        WebElement webElement = getWebElement(userNameSelector);
        webElement.clear();
        webElement.sendKeys(userName);
        return this;
    }

    public CustomerLoginPage fillPassword(String password) {
        WebElement webElement = getWebElement(passwordSelector);
        webElement.clear();
        webElement.sendKeys(password);
        return this;
    }


    public HomePage clickLoginButtonSuccess() {
        WebElement webElement = getWebElement(loginButtonSelector);
        webElement.click();
        return getInstance(HomePage.class);
    }

    public CustomerLoginPage clickLoginButtonFailed() {
        WebElement webElement = getWebElement(loginButtonSelector);
        webElement.click();
        return this;
    }

    public boolean hasError() {
        return getWebElements(errorMessageSelector).size() > 0;
    }


    //=============================================================

    public CustomerLoginPage doLogin() {
        return clickLoginButtonFailed();
    }

    public CustomerLoginPage doLogin(String username) {
        return fillUserName(username).clickLoginButtonFailed();
    }

    public HomePage doLogin(String username, String password) {
        return fillUserName(username)
                .fillPassword(password)
                .clickLoginButtonSuccess();
    }

}
