package com.skillio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.skillio.base.Keyword;

public class DashboardPage {

    WebDriver driver = Keyword.getDriver();

    // Locators
    private By pageHeaderTitle = By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module')]");
    private By menuSearchInput = By.xpath("//input[@placeholder='Search']");
    private By adminMenuLink = By.xpath("//a[contains(@href, 'viewAdminModule')]");

    private By getWidgetHeader(String widgetName) {
        return By.xpath("//p[text()='" + widgetName + "']");
    }

    public String getPageHeader() {
        return driver.findElement(pageHeaderTitle).getText();
    }

    public boolean isWidgetDisplayed(String widgetName) {
        return driver.findElement(getWidgetHeader(widgetName)).isDisplayed();
    }

    public void searchMenu(String searchText) {
        driver.findElement(menuSearchInput).clear();
        driver.findElement(menuSearchInput).sendKeys(searchText);
    }

    public boolean isAdminMenuVisible() {
        return driver.findElement(adminMenuLink).isDisplayed();
    }
}