package com.skillio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class LeavePage {

    public LeavePage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    // ── Locators ─────────────────────────────────────────────────────────────

    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenu;

    @FindBy(xpath = "//a[text()='Apply']")
    private WebElement applyLink;

    @FindBy(xpath = "//a[text()='Leave List']")
    private WebElement leaveListLink;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[contains(text(),'Should be after')]")
    private WebElement dateError;

    @FindBy(xpath = "//button[normalize-space()='Approve']")
    private WebElement approveBtn;

    @FindBy(xpath = "//button[normalize-space()='Reject']")
    private WebElement rejectBtn;

    // ── Actions ──────────────────────────────────────────────────────────────

    public void login(String username, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithCredentials(username, password);
    }

    public void navigateToLeave() {
        WaitFor.elementToBeClickable(leaveMenu);
        leaveMenu.click();
    }

    public void clickApply() {
        WaitFor.elementToBeClickable(applyLink);
        applyLink.click();
    }

    public void clickLeaveList() {
        WaitFor.elementToBeClickable(leaveListLink);
        leaveListLink.click();
    }

    public void applyLeave(String leaveType, String fromDate, String toDate, String comment) {
        // Extend: select leaveType dropdown, enter dates, enter comment
        // Keeping as stub — extend with actual locators
    }

    public void selectLeaveType(String leaveType) {
        // Extend with dropdown selection
    }

    public void enterFromDate(String fromDate) {
        // Extend with date picker locator
    }

    public void enterToDate(String toDate) {
        // Extend with date picker locator
    }

    public void enterComment(String comment) {
        // Extend with comment textarea locator
    }

    public void filterByStatus(String status) {
        // Extend with status dropdown locator
    }

    public void openFirstPendingRequest() {
        // Extend: click first row in leave list
    }

    public void clickApprove() {
        WaitFor.elementToBeClickable(approveBtn);
        approveBtn.click();
    }

    public void clickReject() {
        WaitFor.elementToBeClickable(rejectBtn);
        rejectBtn.click();
    }

    public void clickSubmit() {
        WaitFor.elementToBeClickable(submitBtn);
        submitBtn.click();
    }

    // ── Validations ──────────────────────────────────────────────────────────

    public boolean isSuccessToastDisplayed() {
        WaitFor.elementToBeVisible(successToast);
        return successToast.isDisplayed();
    }

    public boolean isDateErrorDisplayed() {
        return dateError.isDisplayed();
    }
}
