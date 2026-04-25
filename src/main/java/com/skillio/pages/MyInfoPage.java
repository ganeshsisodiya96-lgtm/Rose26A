package com.skillio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;

public class MyInfoPage {

    public MyInfoPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoMenu;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    public void navigateToMyInfo() {
        myInfoMenu.click();
    }

    public void clickSave() {
        saveBtn.click();
    }

    public boolean isSuccessToastDisplayed() {
        return successToast.isDisplayed();
    }
}