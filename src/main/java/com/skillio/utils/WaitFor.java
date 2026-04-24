package com.skillio.utils;

import java.time.Duration;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillio.base.Keyword;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This class contains all the wait related keywords.It will have all the methods
 *  which will wait for the element to be visible, clickable etc. 
 * @author skillio
 */

public class WaitFor {

    // Remove static shared WebDriverWait. Create a new WebDriverWait per call using the current thread's driver.
    
    private static WebDriverWait getWait() {
        RemoteWebDriver driver = Keyword.threadLocal.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver for current thread is not initialized");
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        return wait;
    }

    private WaitFor() {
    }
    
    public static void elementToBeVisible(By element) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(element));

    }
    public static void elementToBeClickable(By element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));

    }
    public static void elementToBePresent(By element) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(element));

    }
}