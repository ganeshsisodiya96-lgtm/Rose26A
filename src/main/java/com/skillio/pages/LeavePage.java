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

    @FindBy(xpath = "//span[text()='Leave']")
    private WebElement leaveMenu;

    @FindBy(xpath = "//a[text()='Apply']")
    private WebElement applyBtn;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[contains(text(),'Should be after')]")
    private WebElement dateError;

    public void navigateToLeave() {
        leaveMenu.click();
    }

    public void applyLeave() {
        applyBtn.click();
        submitBtn.click();
    }

    public boolean isSuccessToastDisplayed() {
        return successToast.isDisplayed();
    }

    public boolean isDateErrorDisplayed() {
        return dateError.isDisplayed();
    }
}