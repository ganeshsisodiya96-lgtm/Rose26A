package com.skillio.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.skillio.base.Keyword;
import com.skillio.utils.WaitFor;

public class LoginPage {

    // Constructor (CORRECT way to initialize PageFactory)
    public LoginPage() {
        PageFactory.initElements(Keyword.getDriver(), this);
    }

    // Locators
    @FindBy(name = "username")
    private WebElement userNameTxtBx;

    @FindBy(name = "password")
    private WebElement passwordTxtBx;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInBtn;

    @FindBy(css = "p.oxd-alert-content-text")
    private WebElement loginError;

    // Actions
    public void enterUserName(String userName) {
        WaitFor.elementToBeClickable(By.name("username"));
        userNameTxtBx.clear();
        userNameTxtBx.sendKeys(userName);
    }

    public void enterPassword(String password) {
        WaitFor.elementToBeClickable(By.name("password"));
        passwordTxtBx.clear();
        passwordTxtBx.sendKeys(password);
    }

    public void clickSignInBtn() {
        WaitFor.elementToBeClickable(By.xpath("//button[@type='submit']"));
        signInBtn.click();
    }

    // Business Method (Clean POM)
    public void loginWithCredentials(String username, String password) {
        enterUserName(username);
        enterPassword(password);
        clickSignInBtn();
    }

    public String getLoginErrorMessage() {
        By errorMsg = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
        WaitFor.elementToBeVisible(errorMsg);
        return Keyword.getDriver().findElement(errorMsg).getText();
    }
    public String getDashboardHeaderText() {
        By header = By.xpath("//h6");
        WaitFor.elementToBePresent(header);
        return Keyword.getDriver().findElement(header).getText();
    }

    public String getUsernameFieldError() {
        By xpath = By.xpath("//input[@name='username']/ancestor::div[1]//span");
        WaitFor.elementToBePresent(xpath);
        return Keyword.getDriver().findElement(xpath).getText();
    }

    public String getPasswordFieldError() {
        By xpath = By.xpath("//input[@name='password']/ancestor::div[1]//span");
        WaitFor.elementToBePresent(xpath);
        return Keyword.getDriver().findElement(xpath).getText();
    }
}