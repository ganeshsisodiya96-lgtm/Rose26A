package com.skillio.stepdefinitions;

import org.testng.Assert;



import com.skillio.pages.LeavePage;


import io.cucumber.java.en.*;

public class LeaveSteps {

    private LeavePage leave;

    private LeavePage getLeavePage() {
        if (leave == null) {
            leave = new LeavePage();
        }
        return leave;
    }

    // ── Navigation ────────────────────────────────────────────────────────────

    @When("user navigates to Leave module")
    public void userNavigatesToLeaveModule() {
        getLeavePage().navigateToLeave();
    }

    @When("user clicks {string} option")
    public void userClicksOption(String option) {
        if ("Apply".equalsIgnoreCase(option)) {
            getLeavePage().clickApply();
        } else if ("Leave List".equalsIgnoreCase(option)) {
            getLeavePage().clickLeaveList();
        }
    }

    @When("user selects leave type {string}")
    public void userSelectsLeaveType(String leaveType) {
        getLeavePage().selectLeaveType(leaveType);
    }

    @When("user enters from date {string} and to date {string}")
    public void userEntersFromAndToDate(String from, String to) {
        getLeavePage().enterFromDate(from);
        getLeavePage().enterToDate(to);
    }

    @When("user enters comment {string}")
    public void userEntersComment(String comment) {
        getLeavePage().enterComment(comment);
    }

    @When("user clicks Apply button")
    public void userClicksApplyButton() {
        getLeavePage().clickSubmit();
    }

    @When("user filters by status {string}")
    public void userFiltersByStatus(String status) {
        getLeavePage().filterByStatus(status);
    }

    @When("user opens first pending leave request")
    public void userOpensFirstPendingLeaveRequest() {
        getLeavePage().openFirstPendingRequest();
    }

    @When("user clicks Approve button")
    public void userClicksApproveButton() {
        getLeavePage().clickApprove();
    }

    @When("user clicks Reject button")
    public void userClicksRejectButton() {
        getLeavePage().clickReject();
    }

    // ── Assertions ────────────────────────────────────────────────────────────

    @Then("leave request should be submitted with status {string}")
    public void leaveRequestShouldBeSubmitted(String status) {
        Assert.assertTrue(getLeavePage().isSuccessToastDisplayed(),
            "Leave request was not submitted successfully. Expected status: " + status);
    }

    @Then("leave date error {string} should be displayed")
    public void leaveDateErrorShouldBeDisplayed(String message) {
        Assert.assertTrue(getLeavePage().isDateErrorDisplayed(),
            "Expected date error: " + message);
    }

    @Then("leave status should change to {string}")
    public void leaveStatusShouldChangeTo(String status) {
        Assert.assertTrue(getLeavePage().isSuccessToastDisplayed(),
            "Leave status was not updated to: " + status);
    }
}
