package com.parabank.parasoft.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * BasePage
* */
public class BasePage extends Page {
    public BasePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getPageTitle() {
        return webDriver.getTitle();
    }

    @Override
    public WebElement getWebElement(By locator) {
        WebElement webElement = null;
        try {
            waitForWebElement(locator);
            webElement = webDriver.findElement(locator);
        } catch (Exception exception) {
            System.out.println(locator.toString() + " Element is Not Found!\n Error:"
                    + exception.getMessage());
        }
        return webElement;
    }

    @Override
    public List<WebElement> getWebElements(By locator) {
        List<WebElement> webElements = null;
        try {
            waitForWebElement(locator);
            webElements = webDriver.findElements(locator);
        } catch (Exception exception) {
            System.out.println(locator.toString() + " Element is Not Found!\n Error:"
                    + exception.getMessage());
        }
        return webElements;
    }

    @Override
    public void waitForWebElement(By locator) {
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception exception) {
            System.out.println(locator.toString() + " Element is Not Found!\n Error:"
                    + exception.getMessage());
        }
    }


}
