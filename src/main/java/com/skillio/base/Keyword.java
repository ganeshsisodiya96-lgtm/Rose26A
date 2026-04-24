package com.skillio.base;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.skillio.exceptions.InvalidBrowserNameException;



/**
 * This class contains all the keywords related to Selenium WebDriver.
 * Keywords are nothing but the methods which perform some action on the web page.
 *  These keywords are used in the test case classes to perform the actions on the web page.
 */
public class Keyword {
    
    // Replace the single static driver with thread-local driver to allow multiple threads to run independently.
    public static final ThreadLocal<RemoteWebDriver> threadLocal = new ThreadLocal<>();
    
    // Helper to get the current thread's driver. May return null if openBrowser wasn't called in this thread.
    public static RemoteWebDriver getDriver() {
        return threadLocal.get();
    }
    
    /**
     * Opens a browser for the current thread and stores it in ThreadLocal.
     */
    public static void openBrowser(String browserName) {
        RemoteWebDriver localDriver;
        if (browserName.equalsIgnoreCase("chrome")) {
            threadLocal.set(new ChromeDriver());
        } else if (browserName.equalsIgnoreCase("firefox")) {
            threadLocal.set(new FirefoxDriver());
        } else {
            throw new InvalidBrowserNameException(browserName);
        }
        
    }

    public static void launchUrl(String Url) {
                threadLocal.get().get(Url);
        System.out.println("Launched Url: "+ Url);
    }
    /**
     * This method will enter text in the text box based on the locator type and locator value pass by the user.Supported locator types are:
     * <ul>
     * <li>id</li>
     * <lI>name</li>
     * <li>className</li>
     * <li>tagName</li>
     * <li>linkText</li>
     * <li>partialLinkText</li>
     * <li>cssSelector</li>
     * <li>xpath</li>
     * </ul>
     */
    public static void enterText(String locatorType,String locatorvalue, String textToEnter) {
               if(locatorType.equalsIgnoreCase("id")) {
           threadLocal.get().findElement(By.id(locatorvalue)).sendKeys(textToEnter);
        } else if (locatorType.equalsIgnoreCase("name")) {
            threadLocal.get().findElement(By.name(locatorvalue)).sendKeys(textToEnter);
        } else if (locatorType.equalsIgnoreCase("className")) {
            threadLocal.get().findElement(By.className(locatorvalue)).sendKeys(textToEnter);
        } else if (locatorType.equalsIgnoreCase("tagName")) {
            threadLocal.get().findElement(By.tagName(locatorvalue)).sendKeys(textToEnter);
        } else if (locatorType.equalsIgnoreCase("linkText")) {
            threadLocal.get().findElement(By.linkText(locatorvalue)).sendKeys(textToEnter);
        } else if (locatorType.equalsIgnoreCase("partialLinkText")) {
            threadLocal.get().findElement(By.partialLinkText(locatorvalue)).sendKeys(textToEnter);
        } else if (locatorType.equalsIgnoreCase("cssSelector")) {
            threadLocal.get().findElement(By.cssSelector(locatorvalue)).sendKeys(textToEnter);
        } else if (locatorType.equalsIgnoreCase("xpath")) {
            threadLocal.get().findElement(By.xpath(locatorvalue)).sendKeys(textToEnter);
        } else {
            throw new InvalidSelectorException(locatorType);
        }

    }
    
    /**
     * This method will click on the web element based on the locator type and locator value pass by the user.Supported locator types are:
     * @param LocatorType
     * @param locatorValue
     */
    public static void clickOn(String LocatorType, String locatorValue) {
       
         if (LocatorType.equalsIgnoreCase("id")) {
             threadLocal.get().findElement(By.id(locatorValue)).click();
         } else if (LocatorType.equalsIgnoreCase("name")) {
             threadLocal.get().findElement(By.name(locatorValue)).click();
         } else if (LocatorType.equalsIgnoreCase("className")) {
             threadLocal.get().findElement(By.className(locatorValue)).click();
         } else if (LocatorType.equalsIgnoreCase("tagName")) {
             threadLocal.get().findElement(By.tagName(locatorValue)).click();
         } else if (LocatorType.equalsIgnoreCase("linkText")) {
             threadLocal.get().findElement(By.linkText(locatorValue)).click();
         } else if (LocatorType.equalsIgnoreCase("partialLinkText")) {
             threadLocal.get().findElement(By.partialLinkText(locatorValue)).click();
         } else if (LocatorType.equalsIgnoreCase("cssSelector")) {
             threadLocal.get().findElement(By.cssSelector(locatorValue)).click();
         } else if (LocatorType.equalsIgnoreCase("xpath")) {
             threadLocal.get().findElement(By.xpath(locatorValue)).click();
         } else {
             throw new InvalidSelectorException(LocatorType);
         }
        

    }
    public static void quitBrowser() {
		threadLocal.get().quit();
		System.out.println("Driver is quite successfully");

	}
    
}