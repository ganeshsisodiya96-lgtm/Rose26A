package com.skillio.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.skillio.base.Keyword;

public class PimPage {

    WebDriver driver;
    WebDriverWait wait;

    public PimPage() {
        this.driver = Keyword.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ================= LOCATORS =================

    @FindBy(xpath = "//span[text()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    private WebElement addEmployeeBtn;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@name='employeeId']")
    private WebElement empId;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[text()='Already exists']")
    private WebElement duplicateIdError;

    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredError;

    // ================= GENERIC WAIT METHODS =================

    private WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForClickability(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    private WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // ================= ACTIONS =================

    public void navigateToPim() {
        waitForClickability(pimMenu).click();
    }

    public void clickAddEmployee() {
        waitForClickability(addEmployeeBtn).click();

        // VERY IMPORTANT → wait for form to load
        waitForVisibility(firstName);
    }

    public void enterEmployeeDetails(String fName, String lName, String id) {

        if (fName != null && !fName.isEmpty()) {
            waitForVisibility(firstName);
            firstName.clear();
            firstName.sendKeys(fName);
        }

        if (lName != null && !lName.isEmpty()) {
            waitForVisibility(lastName);
            lastName.clear();
            lastName.sendKeys(lName);
        }

        if (id != null && !id.isEmpty()) {
            waitForVisibility(empId);
            empId.clear();
            empId.sendKeys(id);
        }
    }

    public void clickSave() {
        waitForClickability(saveBtn).click();
    }

    public void clearNameFields() {
        // ensure we are on correct page
        waitForVisibility(firstName);

        try {
            firstName.clear();
        } catch (Exception e) {
            // fallback in case of stale element
            WebElement element = waitForPresence(By.name("firstName"));
            element.clear();
        }
    }

    // ================= BUSINESS METHOD =================

    public void addEmployee(String fName, String lName, String id) {
        navigateToPim();
        clickAddEmployee();
        enterEmployeeDetails(fName, lName, id);
        clickSave();
    }

    // ================= VALIDATIONS =================

    public boolean isSuccessToastDisplayed() {
        try {
            return waitForVisibility(successToast).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDuplicateIdErrorDisplayed() {
        try {
            return waitForVisibility(duplicateIdError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRequiredErrorDisplayed() {
        try {
            return waitForVisibility(requiredError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

	public void searchEmployee(String name) {
		// TODO Auto-generated method stub
		
	}
}