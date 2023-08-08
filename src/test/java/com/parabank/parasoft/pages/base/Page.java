package com.parabank.parasoft.pages.base;

import com.parabank.parasoft.utils.AppConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class Page {
    public WebDriver webDriver;
    public WebDriverWait webDriverWait;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(this.webDriver, AppConfig.WEB_DRIVER_WAIT_DURATION);
    }


    public abstract String getPageTitle();

    public abstract WebElement getWebElement(By locator);

    public abstract List<WebElement> getWebElements(By locator);

    public abstract void waitForWebElement(By locator);

    //============================================================
    public <T extends BasePage> T getInstance(Class<T> tClass) {
        try {
            return tClass.getDeclaredConstructor(WebDriver.class).newInstance(this.webDriver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


}
