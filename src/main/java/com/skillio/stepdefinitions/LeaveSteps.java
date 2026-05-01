package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.pages.LeavePage;

import io.cucumber.java.en.*;

public class LeaveSteps {

    private LeavePage leave = new LeavePage();

    // ── Navigation ────────────────────────────────────────────────────────────

    @When("user navigates to Leave module")
    public void userNavigatesToLeaveModule() {
        leave.navigateToLeave();
    }

    @When("user clicks {string} option")
    public void userClicksOption(String option) {
        if ("Apply".equalsIgnoreCase(option)) {
            leave.clickApply();
        } else if ("Leave List".equalsIgnoreCase(option)) {
            leave.clickLeaveList();
        }
    }

    @When("user selects leave type {string}")
    public void userSelectsLeaveType(String leaveType) {
        leave.selectLeaveType(leaveType);
    }

    @When("user enters from date {string} and to date {string}")
    public void userEntersFromAndToDate(String from, String to) {
        leave.enterFromDate(from);
        leave.enterToDate(to);
    }

    @When("user enters comment {string}")
    public void userEntersComment(String comment) {
        leave.enterComment(comment);
    }

    @When("user clicks Apply button")
    public void userClicksApplyButton() {
        leave.clickSubmit();
    }

    @When("user filters by status {string}")
    public void userFiltersByStatus(String status) {
        leave.filterByStatus(status);
    }

    @When("user opens first pending leave request")
    public void userOpensFirstPendingLeaveRequest() {
        leave.openFirstPendingRequest();
    }

    @When("user clicks Approve button")
    public void userClicksApproveButton() {
        leave.clickApprove();
    }

    @When("user clicks Reject button")
    public void userClicksRejectButton() {
        leave.clickReject();
    }

    // ── Assertions ────────────────────────────────────────────────────────────

    @Then("leave request should be submitted with status {string}")
    public void leaveRequestShouldBeSubmitted(String status) {
        Assert.assertTrue(leave.isSuccessToastDisplayed(),
            "Leave request was not submitted successfully. Expected status: " + status);
    }

    @Then("leave date error {string} should be displayed")
    public void leaveDateErrorShouldBeDisplayed(String message) {
        Assert.assertTrue(leave.isDateErrorDisplayed(),
            "Expected date error: " + message);
    }

    @Then("leave status should change to {string}")
    public void leaveStatusShouldChangeTo(String status) {
        Assert.assertTrue(leave.isSuccessToastDisplayed(),
            "Leave status was not updated to: " + status);
    }
}