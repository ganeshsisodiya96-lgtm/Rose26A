package com.skillio.utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.skillio.base.Keyword;

public class WaitFor {

    private WaitFor() {}

    private static WebDriverWait getWait() {
        RemoteWebDriver driver = Keyword.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        return wait;
    }

    // ================= NEW: LOADER HANDLING =================

    /**
     * Specifically handles the OrangeHRM spinner/loader.
     * Call this before clicking Save/Submit buttons.
     */
    public static void loaderToBeInvisible() {
        By loader = By.className("oxd-form-loader");
        try {
            getWait().until(ExpectedConditions.invisibilityOfElementLocated(loader));
        } catch (Exception e) {
            // If it's already gone or doesn't appear, continue
        }
    }

    // ================= EXISTING WAITS =================

    public static void elementToBeVisible(By element) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void elementToBeClickable(By element) {
        // Optimization: Ensure loader is gone before checking clickability
        loaderToBeInvisible();
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void elementToBePresent(By element) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void elementToBeVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void elementToBeClickable(WebElement element) {
        loaderToBeInvisible();
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    
    /**
     * Helpful for MyInfo or Recruitment where pages take time to render
     */
    public static void textToBePresentInElement(WebElement element, String text) {
        getWait().until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}