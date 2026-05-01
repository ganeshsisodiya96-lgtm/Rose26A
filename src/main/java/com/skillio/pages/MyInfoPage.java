package com.skillio.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class MyInfoPage {

    private static final By MY_INFO_MENU       = By.xpath("//span[text()='My Info']");
    private static final By PERSONAL_TAB       = By.xpath("//a[normalize-space()='Personal Details']");
    private static final By CONTACT_TAB        = By.xpath("//a[normalize-space()='Contact Details']");
    private static final By EMERGENCY_TAB      = By.xpath("//a[normalize-space()='Emergency Contacts']");
    private static final By SAVE_BTN           = By.xpath("//button[@type='submit']");
    private static final By SUCCESS_TOAST      = By.cssSelector(".oxd-toast-content-text");
    private static final By FIRST_NAME         = By.name("firstName");
    private static final By DOB_FIELD          = By.xpath("//label[normalize-space()='Date of Birth']/following::input[1]");
    private static final By NATIONALITY_DD     = By.xpath("//label[normalize-space()='Nationality']/following::div[contains(@class,'oxd-select-text')][1]");
    private static final By STREET1            = By.xpath("//label[normalize-space()='Street 1']/following::input[1]");
    private static final By CITY               = By.xpath("//label[normalize-space()='City']/following::input[1]");
    private static final By ADD_BTN            = By.xpath("//button[normalize-space()='Add']");
    private static final By CONTACT_NAME       = By.xpath("//label[normalize-space()='Name']/following::input[1]");
    private static final By RELATIONSHIP       = By.xpath("//label[normalize-space()='Relationship']/following::input[1]");
    private static final By HOME_PHONE         = By.xpath("//label[normalize-space()='Home Telephone']/following::input[1]");

    public MyInfoPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    private WebDriver driver() { return Keyword.getDriver(); }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver(), Duration.ofSeconds(30));
    }

    // Find element fresh and click — avoids PageFactory proxy serialization crash
    private void clickDropdown(By dropdownBy) {
        WaitFor.loaderToBeInvisible();
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
        WaitFor.elementToBeVisible(dropdownBy);
        WebElement el = driver().findElement(dropdownBy);
        ((JavascriptExecutor) driver())
            .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        el.click();
    }

    private void jsClick(By btnBy) {
        WaitFor.loaderToBeInvisible();
        WaitFor.elementToBeVisible(btnBy);
        ((JavascriptExecutor) driver())
            .executeScript("arguments[0].click();", driver().findElement(btnBy));
    }

    public void navigateToMyInfo() {
        WaitFor.elementToBeClickable(MY_INFO_MENU);
        driver().findElement(MY_INFO_MENU).click();
    }

    public void clickPersonalDetailsTab() {
        WaitFor.elementToBeClickable(PERSONAL_TAB);
        driver().findElement(PERSONAL_TAB).click();
    }

    public void clickContactDetailsTab() {
        WaitFor.elementToBeClickable(CONTACT_TAB);
        driver().findElement(CONTACT_TAB).click();
    }

    public void clickEmergencyContactsTab() {
        WaitFor.elementToBeClickable(EMERGENCY_TAB);
        driver().findElement(EMERGENCY_TAB).click();
    }

    public void selectNationality(String nationality) {
        clickDropdown(NATIONALITY_DD);
        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + nationality + "']");
        WaitFor.elementToBeVisible(option);
        driver().findElement(option).click();
    }

    public void enterFirstName(String name) {
        WaitFor.elementToBeVisible(FIRST_NAME);
        WebElement el = driver().findElement(FIRST_NAME);
        el.clear();
        el.sendKeys(name);
    }

    public void enterDob(String dob) {
        WaitFor.elementToBeVisible(DOB_FIELD);
        WebElement el = driver().findElement(DOB_FIELD);
        // Clear and set via JS, fire blur to trigger Vue validation
        ((JavascriptExecutor) driver()).executeScript(
            "arguments[0].value = '';" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
            el, dob);
        // Click elsewhere to confirm
        try { driver().findElement(FIRST_NAME).click(); } catch (Exception ignored) {}
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
    }

    public void enterStreet1(String street) {
        WaitFor.elementToBeVisible(STREET1);
        WebElement el = driver().findElement(STREET1);
        el.clear();
        el.sendKeys(street);
    }

    public void enterCity(String city) {
        WaitFor.elementToBeVisible(CITY);
        WebElement el = driver().findElement(CITY);
        el.clear();
        el.sendKeys(city);
    }

    public void clickAdd() {
        WaitFor.elementToBeClickable(ADD_BTN);
        driver().findElement(ADD_BTN).click();
    }

    public void enterContactName(String name) {
        WaitFor.elementToBeVisible(CONTACT_NAME);
        WebElement el = driver().findElement(CONTACT_NAME);
        el.clear();
        el.sendKeys(name);
    }

    public void enterRelationship(String rel) {
        WaitFor.elementToBeVisible(RELATIONSHIP);
        WebElement el = driver().findElement(RELATIONSHIP);
        el.clear();
        el.sendKeys(rel);
    }

    public void enterHomePhone(String phone) {
        WaitFor.elementToBeVisible(HOME_PHONE);
        WebElement el = driver().findElement(HOME_PHONE);
        el.clear();
        el.sendKeys(phone);
    }

    public void clickSave() {
        jsClick(SAVE_BTN);
    }

    public boolean isSuccessToastDisplayed() {
        try { WaitFor.elementToBeVisible(SUCCESS_TOAST);
              return driver().findElement(SUCCESS_TOAST).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isDobErrorDisplayed() {
        // After saving with invalid/blank DOB, OrangeHRM shows a Required or date error span
        By[] candidates = {
            By.xpath("//span[contains(@class,'oxd-input-field-error-message')]"),
            By.xpath("//span[contains(text(),'Should be a valid date')]"),
            By.xpath("//span[contains(text(),'Required') and ancestor::*[.//label[normalize-space()='Date of Birth']]]")
        };
        for (By loc : candidates) {
            try {
                WebDriverWait w = new WebDriverWait(driver(), Duration.ofSeconds(4));
                w.until(ExpectedConditions.visibilityOfElementLocated(loc));
                return driver().findElement(loc).isDisplayed();
            } catch (Exception ignored) {}
        }
        return false;
    }

    public boolean isEmergencyContactInList(String name) {
        try {
            By row = By.xpath("//div[contains(@class,'oxd-table-body')]//div[normalize-space()='" + name + "']");
            WaitFor.elementToBeVisible(row);
            return !driver().findElements(row).isEmpty();
        } catch (Exception e) { return false; }
    }
}