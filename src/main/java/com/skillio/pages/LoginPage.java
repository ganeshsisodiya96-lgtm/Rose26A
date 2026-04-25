package com.skillio.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    // ================= LOCATORS =================
    @FindBy(name = "username")
    private WebElement userNameTxtBx;

    @FindBy(name = "password")
    private WebElement passwordTxtBx;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInBtn;

    @FindBy(css = "p.oxd-alert-content-text")
    private WebElement loginError;

    @FindBy(xpath = "//h6")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//input[@name='username']/ancestor::div[1]//span")
    private WebElement usernameError;

    @FindBy(xpath = "//input[@name='password']/ancestor::div[1]//span")
    private WebElement passwordError;

    // ================= ACTIONS =================

    public void enterUserName(String userName) {
        WaitFor.elementToBeVisible(userNameTxtBx);
        userNameTxtBx.clear();
        userNameTxtBx.sendKeys(userName);
    }

    public void enterPassword(String password) {
        WaitFor.elementToBeVisible(passwordTxtBx);
        passwordTxtBx.clear();
        passwordTxtBx.sendKeys(password);
    }

    public void clickSignInBtn() {
        WaitFor.elementToBeClickable(signInBtn);
        signInBtn.click();
    }

    public void loginWithCredentials(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickSignInBtn();
    }

    // ================= VALIDATIONS =================

    public String getLoginErrorMessage() {
        WaitFor.elementToBeVisible(loginError);
        return loginError.getText();
    }

    public String getDashboardHeaderText() {
        WaitFor.elementToBeVisible(dashboardHeader);
        return dashboardHeader.getText();
    }

    public String getUsernameFieldError() {
        WaitFor.elementToBeVisible(usernameError);
        return usernameError.getText();
    }

    public String getPasswordFieldError() {
        WaitFor.elementToBeVisible(passwordError);
        return passwordError.getText();
    }
}