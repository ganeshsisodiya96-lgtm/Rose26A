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
    private WebElement vacancyNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredError;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//span[contains(text(),'Expected format')]")
    private WebElement emailError;

    // ── Actions ────────────────────────────────────────────────────────────

    public void navigateToRecruitment() {
        WaitFor.elementToBeClickable(recruitmentMenu);
        recruitmentMenu.click();
    }

    public void clickAddVacancy() {
        WaitFor.elementToBeClickable(addBtn);
        addBtn.click();
    }

    public void enterVacancyName(String name) {
        WaitFor.elementToBeVisible(vacancyNameField);
        vacancyNameField.clear();
        vacancyNameField.sendKeys(name);
    }

    public void selectJobTitle(String title) {
        // Extend with dropdown logic as needed
    }

    public void enterPositionCount(String count) {
        // Extend with input field for position count
    }

    public void openVacancy(String name) {
        // Navigate to vacancy by name – extend as needed
    }

    public void clickAddCandidate() {
        WaitFor.elementToBeClickable(addBtn);
        addBtn.click();
    }

    public void enterCandidateName(String first, String last) {
        // Extend with first/last name fields for candidate form
    }

    public void enterCandidateEmail(String email) {
        WaitFor.elementToBeVisible(emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void selectVacancyInSearch(String name) {
        // Extend with vacancy dropdown in search form
    }

    public void clickSave() {
        WaitFor.elementToBeClickable(saveBtn);
        saveBtn.click();
    }

    public void addCandidate(String first, String last, String email) {
        enterCandidateName(first, last);
        enterCandidateEmail(email);
        clickSave();
    }

    // ── Validations ────────────────────────────────────────────────────────

    public boolean isSuccessToastDisplayed() {
        WaitFor.elementToBeVisible(successToast);
        return successToast.isDisplayed();
    }

    public boolean isRequiredErrorDisplayed() {
        return requiredError.isDisplayed();
    }

    public boolean isEmailErrorDisplayed() {
        return emailError.isDisplayed();
    }
}
