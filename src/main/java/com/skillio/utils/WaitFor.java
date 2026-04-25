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

    // Centralized wait creator
    private static WebDriverWait getWait() {
        RemoteWebDriver driver = Keyword.getDriver(); // ✅ correct way

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);

        return wait;
    }

    // ================= WAITS =================

    public static void elementToBeVisible(By element) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void elementToBeClickable(By element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void elementToBePresent(By element) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(element));
    }
    public static void elementToBeVisible(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public static void elementToBeClickable(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }
}