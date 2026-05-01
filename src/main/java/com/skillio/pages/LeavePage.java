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

public class LeavePage {

    // XPaths as constants — elements found fresh each time to avoid proxy issues
    private static final By LEAVE_MENU       = By.xpath("//span[text()='Leave']");
    private static final By APPLY_LINK       = By.xpath("//a[normalize-space()='Apply']");
    private static final By LEAVE_LIST_LINK  = By.xpath("//a[normalize-space()='Leave List']");
    private static final By LEAVE_TYPE_DD    = By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");
    private static final By FROM_DATE        = By.xpath("//label[normalize-space()='From Date']/following::input[1]");
    private static final By TO_DATE          = By.xpath("//label[normalize-space()='To Date']/following::input[1]");
    private static final By COMMENTS         = By.xpath("//label[normalize-space()='Comments']/following::textarea[1]");
    private static final By SUBMIT_BTN       = By.xpath("//button[@type='submit']");
    private static final By SUCCESS_TOAST    = By.cssSelector(".oxd-toast-content-text");
    private static final By DATE_ERROR       = By.xpath("//span[contains(text(),'To date should be after from date')]");
    private static final By APPROVE_BTN      = By.xpath("//button[normalize-space()='Approve']");
    private static final By REJECT_BTN       = By.xpath("//button[normalize-space()='Reject']");
    private static final By FIRST_LEAVE_ROW  = By.xpath("(//div[contains(@class,'oxd-table-body')]//div[contains(@class,'oxd-table-row')])[1]");

    public LeavePage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    private WebDriver driver() { return Keyword.getDriver(); }

    private WebDriverWait getWait() {
        return new WebDriverWait(driver(), Duration.ofSeconds(30));
    }

    // Wait for spinner + extra buffer, then find element fresh and click it
    private void clickDropdown(By dropdownBy) {
        // 1. Wait for spinner to disappear
        WaitFor.loaderToBeInvisible();
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
        // 2. Find element fresh (no proxy)
        WaitFor.elementToBeVisible(dropdownBy);
        WebElement el = driver().findElement(dropdownBy);
        // 3. Scroll into view
        ((JavascriptExecutor) driver())
            .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        // 4. Click directly — element is fresh, no proxy serialization issue
        el.click();
    }

    private void jsClick(By btnBy) {
        WaitFor.loaderToBeInvisible();
        WaitFor.elementToBeVisible(btnBy);
        WebElement el = driver().findElement(btnBy);
        ((JavascriptExecutor) driver()).executeScript("arguments[0].click();", el);
    }

    public void navigateToLeave() {
        WaitFor.elementToBeClickable(LEAVE_MENU);
        driver().findElement(LEAVE_MENU).click();
    }

    public void clickApply() {
        WaitFor.elementToBeClickable(APPLY_LINK);
        driver().findElement(APPLY_LINK).click();
    }

    public void clickLeaveList() {
        WaitFor.elementToBeClickable(LEAVE_LIST_LINK);
        driver().findElement(LEAVE_LIST_LINK).click();
    }

    public void selectLeaveType(String leaveType) {
        clickDropdown(LEAVE_TYPE_DD);
        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + leaveType + "']");
        WaitFor.elementToBeVisible(option);
        driver().findElement(option).click();
    }

    public void enterFromDate(String fromDate) {
        WaitFor.elementToBeVisible(FROM_DATE);
        WebElement el = driver().findElement(FROM_DATE);
        el.clear();
        el.sendKeys(fromDate);
        try { driver().findElement(By.tagName("body")).click(); } catch (Exception ignored) {}
    }

    public void enterToDate(String toDate) {
        WaitFor.elementToBeVisible(TO_DATE);
        WebElement el = driver().findElement(TO_DATE);
        el.clear();
        el.sendKeys(toDate);
        try { driver().findElement(By.tagName("body")).click(); } catch (Exception ignored) {}
    }

    public void enterComment(String comment) {
        WaitFor.elementToBeVisible(COMMENTS);
        WebElement el = driver().findElement(COMMENTS);
        el.clear();
        el.sendKeys(comment);
    }

    public void filterByStatus(String status) {
        // Leave List page — find all dropdowns and use the last one (Status is typically last)
        WaitFor.loaderToBeInvisible();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        // Find all oxd-select-text elements on page and pick the last one as Status
        java.util.List<WebElement> dropdowns = driver().findElements(
            By.xpath("//div[contains(@class,'oxd-select-text')]"));
        if (dropdowns.isEmpty()) throw new RuntimeException("No dropdowns found on Leave List page");
        WebElement statusDd = dropdowns.get(dropdowns.size() - 1);
        ((JavascriptExecutor) driver())
            .executeScript("arguments[0].scrollIntoView({block:'center'});", statusDd);
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        statusDd.click();
        By option = By.xpath("//div[@role='listbox']//span[normalize-space()='" + status + "']");
        WaitFor.elementToBeVisible(option);
        driver().findElement(option).click();
    }

    public void openFirstPendingRequest() {
        WaitFor.elementToBeVisible(FIRST_LEAVE_ROW);
        driver().findElement(FIRST_LEAVE_ROW).click();
    }

    public void clickApprove() {
        WaitFor.elementToBeClickable(APPROVE_BTN);
        driver().findElement(APPROVE_BTN).click();
    }

    public void clickReject() {
        WaitFor.elementToBeClickable(REJECT_BTN);
        driver().findElement(REJECT_BTN).click();
    }

    public void clickSubmit() {
        jsClick(SUBMIT_BTN);
    }

    public boolean isSuccessToastDisplayed() {
        try { WaitFor.elementToBeVisible(SUCCESS_TOAST);
              return driver().findElement(SUCCESS_TOAST).isDisplayed(); }
        catch (Exception e) { return false; }
    }

    public boolean isDateErrorDisplayed() {
        try { WaitFor.elementToBeVisible(DATE_ERROR);
              return driver().findElement(DATE_ERROR).isDisplayed(); }
        catch (Exception e) { return false; }
    }
}