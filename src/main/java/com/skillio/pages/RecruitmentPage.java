package com.skillio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class RecruitmentPage {

    public RecruitmentPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    @FindBy(xpath = "//span[text()='Recruitment']")
    private WebElement recruitmentMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addBtn;

    @FindBy(xpath = "//input[@placeholder='Vacancy Name']")
    private WebElement vacancyName;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredError;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    // ACTIONS
    public void navigateToRecruitment() {
        WaitFor.elementToBeClickable(recruitmentMenu);
        recruitmentMenu.click();
    }

    public void clickAdd() {
        addBtn.click();
    }

    public void enterVacancyName(String name) {
        vacancyName.sendKeys(name);
    }

    public void clickSave() {
        saveBtn.click();
    }

    public void addCandidate(String email) {
        emailField.sendKeys(email);
        clickSave();
    }

    // VALIDATION
    public boolean isSuccessToastDisplayed() {
        return successToast.isDisplayed();
    }

    public boolean isRequiredErrorDisplayed() {
        return requiredError.isDisplayed();
    }
}