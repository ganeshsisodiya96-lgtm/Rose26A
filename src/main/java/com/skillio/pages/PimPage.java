package com.skillio.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @FindBy(xpath = "//label[normalize-space()='Employee Id']/following::input[1]")
    private WebElement empId;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveBtn;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    private WebElement searchBtn;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    private WebElement employeeSearchInput;

    @FindBy(css = ".oxd-toast-content-text")
    private WebElement successToast;

    @FindBy(xpath = "//span[contains(text(),'Already exists') or contains(text(),'already exists')]")
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
        // Wait for the OrangeHRM form-loader spinner to disappear first
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("oxd-form-loader")));
        } catch (Exception ignored) {}
        // Use JavaScript click to bypass any residual overlay
        waitForClickability(saveBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
    }

    public void clickSearch() {
        waitForClickability(searchBtn).click();
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
            By errorLocator = By.xpath(
                "//span[contains(text(),'Already exists') or contains(text(),'already exists')]");
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            longWait.until(org.openqa.selenium.support.ui.ExpectedConditions
                .visibilityOfElementLocated(errorLocator));
            return driver.findElement(errorLocator).isDisplayed();
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
		waitForVisibility(employeeSearchInput);
		employeeSearchInput.clear();
		employeeSearchInput.sendKeys(name);
	}
}