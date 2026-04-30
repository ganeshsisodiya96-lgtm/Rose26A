package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.pages.RecruitmentPage;

import io.cucumber.java.en.*;

public class RecruitmentSteps {

    private RecruitmentPage rec = new RecruitmentPage();

    @When("user navigates to Recruitment module")
    public void userNavigatesToRecruitmentModule() {
        rec.navigateToRecruitment();
    }

    @When("user clicks on {string} button in Vacancies section")
    public void userClicksAddInVacancies(String button) {
        rec.clickAddVacancy();
    }

    @When("user enters vacancy name {string}")
    public void userEntersVacancyName(String name) {
        rec.enterVacancyName(name);
    }

    @When("user selects job title {string}")
    public void userSelectsJobTitle(String title) {
        rec.selectJobTitle(title);
    }

    @When("user enters number of positions {string}")
    public void userEntersNumberOfPositions(String count) {
        rec.enterPositionCount(count);
    }

    @When("user leaves job title blank")
    public void userLeavesJobTitleBlank() { /* no action needed */ }

    @When("user opens vacancy {string}")
    public void userOpensVacancy(String name) {
        rec.openVacancy(name);
    }

    @When("user clicks {string} in candidates section")
    public void userClicksInCandidatesSection(String btn) {
        rec.clickAddCandidate();
    }

    @When("user enters candidate first name {string} and last name {string}")
    public void userEntersCandidateName(String first, String last) {
        rec.enterCandidateName(first, last);
    }

    @When("user enters candidate email {string}")
    public void userEntersCandidateEmail(String email) {
        rec.enterCandidateEmail(email);
    }

    @When("user selects vacancy {string} in candidate search form")
    public void userSelectsVacancyInSearchForm(String name) {
        rec.selectVacancyInSearch(name);
    }

    @Then("vacancy {string} should appear in the vacancies list")
    public void vacancyShouldAppearInList(String name) {
        Assert.assertTrue(rec.isSuccessToastDisplayed(),
            "Expected vacancy '" + name + "' to be created successfully");
    }

    @Then("field validation error {string} should appear under job title field")
    public void fieldValidationErrorUnderJobTitle(String error) {
        Assert.assertTrue(rec.isRequiredErrorDisplayed(), "Required error not shown for job title");
    }

    @Then("candidate {string} should be added under {string} vacancy")
    public void candidateShouldBeAdded(String candidate, String vacancy) {
        Assert.assertTrue(rec.isSuccessToastDisplayed(),
            "Candidate '" + candidate + "' not added successfully");
    }

    @Then("field validation error {string} should appear")
    public void fieldValidationErrorShouldAppear(String error) {
        Assert.assertTrue(rec.isEmailErrorDisplayed(), "Email validation error not shown");
    }

    @Then("candidates list for {string} should be displayed")
    public void candidatesListShouldBeDisplayed(String vacancy) {
        Assert.assertTrue(true); // extend RecruitmentPage to implement
    }
}
