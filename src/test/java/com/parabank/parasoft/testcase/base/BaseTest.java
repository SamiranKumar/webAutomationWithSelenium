package com.parabank.parasoft.testcase.base;

import com.parabank.parasoft.pages.base.BasePage;
import com.parabank.parasoft.pages.base.Page;
import com.parabank.parasoft.utils.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public WebDriver webDriver;
    public Page page;
    private Properties properties;


    public BaseTest() {
        properties = new Properties();

        try {
            //  String propertyFilePath = System.getProperty("user.dir" + AppConfig.PROJECT_ABSOLUTE_PATH);
            String propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/config.properties";

            properties.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    final String chromeBrowser = "chrome";
    final String firefoxBrowser = "firefox";
    final String chromeHeadlessBrowser = "chromeHeadless";
    final String firefoxHeadlessBrowser = "firefoxHeadless";

    final String baseUrl = "https://parabank.parasoft.com/parabank/index.htm";

    ///======================================================
    @BeforeMethod
    public void setupBrowser() {

        switch (getBrowser()) {
            case chromeBrowser:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;

            case firefoxBrowser:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;

            case chromeHeadlessBrowser:

                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                webDriver = new ChromeDriver(options);

                break;

            case firefoxHeadlessBrowser:

                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--headless");
                webDriver = new FirefoxDriver(firefoxOptions);

                break;

            default:
                System.out.println("Please provide proper Browser Name");
                break;
        }


        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();

        page = new BasePage(webDriver);

    }


    @AfterMethod
    public void dearTown() {
        webDriver.quit();
    }


    //=======================================================

    private String getBrowser() {
        return properties.getProperty("browserName");
    }


    public String getUserName() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

}
