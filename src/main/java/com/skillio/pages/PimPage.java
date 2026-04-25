package com.skillio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class PimPage {

    public PimPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    // LOCATORS
    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addEmployeeBtn;

    @FindBy(name = "firstName")
    private WebElement firstName;

    @FindBy(name = "lastName")
    private WebElement lastName;

    @FindBy(name = "employeeId")
    private WebElement empId;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[text()='Already exists']")
    private WebElement duplicateIdError;

    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredError;

    // ACTIONS
    public void navigateToPim() {
        WaitFor.elementToBeClickable(pimMenu);
        pimMenu.click();
    }

    public void clickAddEmployee() {
        WaitFor.elementToBeClickable(addEmployeeBtn);
        addEmployeeBtn.click();
    }

    public void enterEmployeeDetails(String fName, String lName, String id) {

        if (!fName.isEmpty()) {
            WaitFor.elementToBeVisible(firstName);
            firstName.clear();
            firstName.sendKeys(fName);
        }

        if (!lName.isEmpty()) {
            lastName.clear();
            lastName.sendKeys(lName);
        }

        if (!id.isEmpty()) {
            empId.clear();
            empId.sendKeys(id);
        }
    }

    public void clickSave() {
        WaitFor.elementToBeClickable(saveBtn);
        saveBtn.click();
    }

    public void searchEmployee(String name) {
        // You can extend with search locator
    }

    public void clearNameFields() {
        firstName.clear();
        lastName.clear();
    }

    // BUSINESS METHOD
    public void addEmployee(String fName, String lName, String id) {
        navigateToPim();
        clickAddEmployee();
        enterEmployeeDetails(fName, lName, id);
        clickSave();
    }

    // VALIDATIONS
    public boolean isSuccessToastDisplayed() {
        WaitFor.elementToBeVisible(successToast);
        return successToast.isDisplayed();
    }

    public boolean isDuplicateIdErrorDisplayed() {
        return duplicateIdError.isDisplayed();
    }

    public boolean isRequiredErrorDisplayed() {
        return requiredError.isDisplayed();
    }
}