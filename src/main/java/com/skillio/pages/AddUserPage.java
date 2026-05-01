package com.skillio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class AddUserPage {

    @FindBy(xpath = "//label[normalize-space()='User Role']/following::div[contains(@class,'oxd-select-text')][1]")
    WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[normalize-space()='Admin']")
    WebElement userRoleAdminOption;

    @FindBy(xpath = "//label[normalize-space()='Status']/following::div[contains(@class,'oxd-select-text')]")
    WebElement statusDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[normalize-space()='Enabled']")
    WebElement statusEnabledOption;

    @FindBy(xpath = "//label[normalize-space()='Username']/following::input[1]")
    WebElement usernameInput;

    @FindBy(xpath = "//label[normalize-space()='Password']/following::input[1]")
    WebElement passwordInput;

    @FindBy(xpath = "//label[normalize-space()='Confirm Password']/following::input[1]")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    WebElement saveButton;

    {
        PageFactory.initElements(Keyword.threadLocal.get(), this);
    }

    public void selectUserRoleAdmin() {
        WaitFor.elementToBeClickable(By.xpath("//label[normalize-space()='User Role']/following::div[contains(@class,'oxd-select-text')][1]"));
        userRoleDropdown.click();
        WaitFor.elementToBeClickable(By.xpath("//div[@role='listbox']//span[normalize-space()='Admin']"));
        userRoleAdminOption.click();
    }

    public void enterEmployeeNameAndSelect(String employeeName) {
        By employeeInput = By.xpath("//input[@placeholder='Type for hints...']");
        WaitFor.elementToBeVisible(employeeInput);
        Keyword.threadLocal.get().findElement(employeeInput).clear();
        Keyword.threadLocal.get().findElement(employeeInput).sendKeys(employeeName);
        // Use contains() so partial display name matches work
        String firstWord = employeeName.split(" ")[0];
        By suggestion = By.xpath("//div[@role='listbox']//span[contains(normalize-space(),'" + firstWord + "')]");
        WaitFor.elementToBeVisible(suggestion);
        Keyword.threadLocal.get().findElement(suggestion).click();
    }

    public void selectStatusEnabled() {
        WaitFor.elementToBeClickable(By.xpath("//label[normalize-space()='Status']/following::div[contains(@class,'oxd-select-text')]"));
        statusDropdown.click();
        WaitFor.elementToBeClickable(By.xpath("//div[@role='listbox']//span[normalize-space()='Enabled']"));
        statusEnabledOption.click();
    }

    public void enterUsername(String username) {
        WaitFor.elementToBeVisible(By.xpath("//label[normalize-space()='Username']/following::input[1]"));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPasswordAndConfirm(String password) {
        WaitFor.elementToBeVisible(By.xpath("//label[normalize-space()='Password']/following::input[1]"));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        WaitFor.elementToBeVisible(By.xpath("//label[normalize-space()='Confirm Password']/following::input[1]"));
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(password);
    }

    public void clickSave() {
        WaitFor.elementToBeClickable(By.xpath("//button[normalize-space()='Save']"));
        saveButton.click();
    }
}