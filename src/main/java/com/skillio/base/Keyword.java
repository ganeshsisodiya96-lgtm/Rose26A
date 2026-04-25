package com.skillio.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.skillio.exceptions.InvalidBrowserNameException;

public class Keyword {

    public static final ThreadLocal<RemoteWebDriver> threadLocal = new ThreadLocal<>();

    public static RemoteWebDriver getDriver() {
        RemoteWebDriver driver = threadLocal.get();
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized for this thread.");
        }
        return driver;
    }

    
    public static void openBrowser(String browserName) {
        RemoteWebDriver driver;

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new InvalidBrowserNameException(browserName);
        }

        threadLocal.set(driver);

        
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    public static void launchUrl(String url) {
        getDriver().get(url);
        System.out.println("Launched Url: " + url);
    }

    
    public static void enterText(String locatorType, String locatorValue, String text) {
        getElement(locatorType, locatorValue).sendKeys(text);
    }

    public static void clickOn(String locatorType, String locatorValue) {
        getElement(locatorType, locatorValue).click();
    }

    private static org.openqa.selenium.WebElement getElement(String type, String value) {
        RemoteWebDriver driver = getDriver();

        switch (type.toLowerCase()) {
            case "id": return driver.findElement(By.id(value));
            case "name": return driver.findElement(By.name(value));
            case "classname": return driver.findElement(By.className(value));
            case "tagname": return driver.findElement(By.tagName(value));
            case "linktext": return driver.findElement(By.linkText(value));
            case "partiallinktext": return driver.findElement(By.partialLinkText(value));
            case "cssselector": return driver.findElement(By.cssSelector(value));
            case "xpath": return driver.findElement(By.xpath(value));
            default: throw new InvalidSelectorException(type);
        }
    }

   
    public static void quitBrowser() {
        RemoteWebDriver driver = threadLocal.get();

        if (driver == null) {
            System.out.println("No driver found for this thread.");
            return;
        }

        try {
            driver.quit();
            System.out.println("Driver quit successfully");
        } catch (Exception e) {
            System.err.println("Error while quitting: " + e.getMessage());
        } finally {
            threadLocal.remove(); 
        }
    }
}