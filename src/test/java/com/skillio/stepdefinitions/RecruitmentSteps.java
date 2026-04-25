package com.skillio.stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.*;

import pages.RecruitmentPage;
import utils.DriverManager;

public class RecruitmentSteps {

    private RecruitmentPage rec = new RecruitmentPage(DriverManager.getDriver());

    @When("user creates vacancy {string} {string} {string}")
    public void createVacancy(String name, String job, String count) {
        rec.navigateToRecruitment();
        rec.createVacancy(name, job, count);
    }

    @Then("vacancy result should be {string}")
    public void verifyVacancy(String result) {

        if ("success".equalsIgnoreCase(result)) {
            Assert.assertTrue(rec.isSuccessToastDisplayed());
        } else {
            Assert.assertTrue(rec.isRequiredErrorDisplayed());
        }
    }

    @When("user adds candidate email {string}")
    public void addCandidate(String email) {
        rec.addCandidate("Test", "User", email);
    }

    @Then("candidate result should be {string}")
    public void verifyCandidate(String result) {

        if ("success".equalsIgnoreCase(result)) {
            Assert.assertTrue(rec.isSuccessToastDisplayed());
        } else {
            Assert.assertTrue(rec.isEmailErrorDisplayed());
        }
    }
}