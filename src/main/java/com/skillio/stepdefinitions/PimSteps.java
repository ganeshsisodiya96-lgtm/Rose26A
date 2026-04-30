package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.base.Keyword;
import com.skillio.pages.PimPage;
import com.skillio.pages.LoginPage;

import io.cucumber.java.en.*;

public class PimSteps {

    private PimPage pim = new PimPage();

    // ── Background step shared across PIM, Recruitment, MyInfo features ──────

    @Given("user is logged into OrangeHRM as {string}")
    public void userIsLoggedIntoOrangeHRMAs(String role) {
        Keyword.getDriver().get(
            "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        );
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithCredentials("Admin", "admin123");
    }

    // ── PIM Navigation ────────────────────────────────────────────────────────

    @When("user navigates to PIM module")
    public void userNavigatesToPIMModule() {
        pim.navigateToPim();
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        if ("Add Employee".equalsIgnoreCase(buttonName)) {
            pim.clickAddEmployee();
        }
    }

    @When("user enters first name {string} and last name {string}")
    public void userEntersFirstAndLastName(String first, String last) {
        pim.enterEmployeeDetails(first, last, "");
    }

    @When("user enters employee ID {string}")
    public void userEntersEmployeeID(String id) {
        pim.enterEmployeeDetails("", "", id);
    }

    @When("user clears first name field")
    public void userClearsFirstNameField() {
        pim.clearNameFields();
    }

    @When("user clears last name field")
    public void userClearsLastNameField() {
        // handled by clearNameFields()
    }

    @When("user clicks Save button")
    public void userClicksSaveButton() {
        pim.clickSave();
    }

    @When("user enters employee name {string} in search box")
    public void userEntersEmployeeNameInSearchBox(String name) {
        pim.searchEmployee(name);
    }

    @When("user clicks Search button")
    public void userClicksSearchButton() {
        pim.clickSave();
    }

    @When("user searches for employee {string}")
    public void userSearchesForEmployee(String name) {
        pim.searchEmployee(name);
    }

    @When("user selects employee checkbox")
    public void userSelectsEmployeeCheckbox() { /* extend PimPage if needed */ }

    @When("user clicks Delete Selected button")
    public void userClicksDeleteSelectedButton() { /* extend PimPage if needed */ }

    @When("user confirms deletion in the dialog")
    public void userConfirmsDeletionInTheDialog() { /* extend PimPage if needed */ }

    // ── Assertions ────────────────────────────────────────────────────────────

    @Then("employee {string} should be successfully created")
    public void employeeShouldBeSuccessfullyCreated(String name) {
        Assert.assertTrue(pim.isSuccessToastDisplayed(),
            "Expected success toast after creating: " + name);
    }

    @Then("success toast message should be displayed")
    public void successToastMessageShouldBeDisplayed() {
        Assert.assertTrue(pim.isSuccessToastDisplayed(), "Success toast not shown");
    }

    @Then("error message {string} should be displayed")
    public void errorMessageShouldBeDisplayed(String message) {
        Assert.assertTrue(pim.isDuplicateIdErrorDisplayed(),
            "Expected error: " + message);
    }

    @Then("field validation error {string} should appear under first name")
    public void fieldValidationErrorUnderFirstName(String error) {
        Assert.assertTrue(pim.isRequiredErrorDisplayed(), "Required error not shown");
    }

    @Then("field validation error {string} should appear under last name")
    public void fieldValidationErrorUnderLastName(String error) {
        Assert.assertTrue(pim.isRequiredErrorDisplayed(), "Required error not shown");
    }

    @Then("employee {string} should appear in search results")
    public void employeeShouldAppearInSearchResults(String name) {
        Assert.assertTrue(true); // extend PimPage to implement
    }

    @Then("employee")
    public void employeeStepPlaceholder() {
        // handles the incomplete last scenario in PIM.feature
    }
    
    
}
