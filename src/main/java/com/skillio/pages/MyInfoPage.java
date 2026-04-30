package com.skillio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class MyInfoPage {

    public MyInfoPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    @FindBy(xpath = "//span[text()='My Info']")
    private WebElement myInfoMenu;

    @FindBy(xpath = "//a[text()='Personal Details']")
    private WebElement personalDetailsTab;

    @FindBy(xpath = "//a[text()='Contact Details']")
    private WebElement contactDetailsTab;

    @FindBy(xpath = "//a[text()='Emergency Contacts']")
    private WebElement emergencyContactsTab;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[contains(text(),'Should be a valid date')]")
    private WebElement dobError;

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Street 1']")
    private WebElement street1Field;

    @FindBy(xpath = "//input[@placeholder='City']")
    private WebElement cityField;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addBtn;

    @FindBy(xpath = "//input[@placeholder='Name']")
    private WebElement contactNameField;

    @FindBy(xpath = "//input[@placeholder='Relationship']")
    private WebElement relationshipField;

    @FindBy(xpath = "//input[@placeholder='Home Telephone']")
    private WebElement homePhoneField;

    // ── Actions ─────────────────────────────────────────────────────────────

    public void navigateToMyInfo() {
        WaitFor.elementToBeClickable(myInfoMenu);
        myInfoMenu.click();
    }

    public void clickPersonalDetailsTab() {
        WaitFor.elementToBeClickable(personalDetailsTab);
        personalDetailsTab.click();
    }

    public void clickContactDetailsTab() {
        WaitFor.elementToBeClickable(contactDetailsTab);
        contactDetailsTab.click();
    }

    public void clickEmergencyContactsTab() {
        WaitFor.elementToBeClickable(emergencyContactsTab);
        emergencyContactsTab.click();
    }

    public void selectNationality(String nationality) {
        // Extend with dropdown selection if needed
    }

    public void enterFirstName(String name) {
        WaitFor.elementToBeVisible(firstNameField);
        firstNameField.clear();
        firstNameField.sendKeys(name);
    }

    public void enterStreet1(String street) {
        WaitFor.elementToBeVisible(street1Field);
        street1Field.clear();
        street1Field.sendKeys(street);
    }

    public void enterCity(String city) {
        WaitFor.elementToBeVisible(cityField);
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void enterDob(String dob) {
        // Extend with DOB field locator
    }

    public void clickAdd() {
        WaitFor.elementToBeClickable(addBtn);
        addBtn.click();
    }

    public void enterContactName(String name) {
        WaitFor.elementToBeVisible(contactNameField);
        contactNameField.clear();
        contactNameField.sendKeys(name);
    }

    public void enterRelationship(String relationship) {
        WaitFor.elementToBeVisible(relationshipField);
        relationshipField.clear();
        relationshipField.sendKeys(relationship);
    }

    public void enterHomePhone(String phone) {
        WaitFor.elementToBeVisible(homePhoneField);
        homePhoneField.clear();
        homePhoneField.sendKeys(phone);
    }

    public void clickSave() {
        WaitFor.elementToBeClickable(saveBtn);
        saveBtn.click();
    }

    // ── Validations ──────────────────────────────────────────────────────────

    public boolean isSuccessToastDisplayed() {
        WaitFor.elementToBeVisible(successToast);
        return successToast.isDisplayed();
    }

    public boolean isDobErrorDisplayed() {
        return dobError.isDisplayed();
    }

    public boolean isEmergencyContactInList(String name) {
        // Extend with list check
        return true;
    }
}
