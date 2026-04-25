package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.pages.HomePage;

import io.cucumber.java.en.*;

import pages.PimPage;
import utils.DriverManager;

public class PimSteps {

    private HomePage pim = new HomePage(DriverManager.getDriver());

    @When("user adds employee {string} {string} with id {string}")
    public void addEmployee(String first, String last, String id) {
        pim.navigateToPim();
        pim.addEmployee(first, last, id);
    }

    @Then("employee creation should be {string}")
    public void verifyEmployeeCreation(String result) {

        if ("success".equalsIgnoreCase(result)) {
            Assert.assertTrue(pim.isSuccessToastDisplayed());
        } else {
            Assert.assertTrue(
                pim.isDuplicateIdErrorDisplayed() ||
                pim.isRequiredErrorDisplayed()
            );
        }
    }

    @When("user searches employee {string}")
    public void searchEmployee(String name) {
        pim.searchEmployee(name);
    }

    @Then("employee {string} should be {string}")
    public void verifyEmployee(String name, String status) {

        if ("present".equalsIgnoreCase(status)) {
            Assert.assertTrue(pim.isEmployeeInList(name));
        } else {
            Assert.assertTrue(pim.isNoRecordsFound());
        }
    }
}