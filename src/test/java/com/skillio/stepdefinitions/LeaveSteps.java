package com.skillio.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.skillio.pages.LeavePage;
import com.skillio.base.Keyword;

public class LeaveSteps {

    // ✅ Lazy init — driver is guaranteed to exist by the time any step runs
    //    (test hooks should initialize the driver before this class is used)
    private LeavePage leave;

    private LeavePage getLeavePage() {
        if (leave == null) {
            leave = new LeavePage();
        }
        return leave;
    }

    // ─────────────────────────────────────────────
    // BACKGROUND / SETUP
    // ─────────────────────────────────────────────

    @Given("user is logged in as {string}")
    public void userIsLoggedIn(String role) {
        Keyword.getDriver().get(
            "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
        );
        // Route to correct credentials based on role
        if ("Admin".equalsIgnoreCase(role)) {
            getLeavePage().login("Admin", "admin123");
        } else {
            getLeavePage().login("employee_user", "employee123");
        }
    }

    // ─────────────────────────────────────────────
    // APPLY LEAVE
    // ─────────────────────────────────────────────

    @When("user applies leave from {string} to {string}")
    public void applyLeave(String from, String to) {
        getLeavePage().navigateToLeave();
        getLeavePage().clickApply();
        getLeavePage().applyLeave("Annual Leave", from, to, "");
    }

    @Then("leave result should be {string}")
    public void verifyLeaveResult(String result) {
        if ("success".equalsIgnoreCase(result)) {
            Assert.assertTrue(
                getLeavePage().isSuccessToastDisplayed(),
                "Expected success toast but it was not displayed"
            );
        } else {
            Assert.assertTrue(
                getLeavePage().isDateErrorDisplayed(),
                "Expected date validation error but it was not displayed"
            );
        }
    }

    // ─────────────────────────────────────────────
    // APPROVE LEAVE
    // ─────────────────────────────────────────────

    @When("user approves leave")
    public void approveLeave() {
        getLeavePage().navigateToLeave();
        getLeavePage().clickLeaveList();
        getLeavePage().openFirstPendingRequest();
        getLeavePage().clickApprove();
    }

    // ─────────────────────────────────────────────
    // REJECT LEAVE
    // ─────────────────────────────────────────────

    @When("user rejects leave")
    public void rejectLeave() {
        getLeavePage().navigateToLeave();
        getLeavePage().clickLeaveList();
        getLeavePage().openFirstPendingRequest();
        getLeavePage().clickReject();
    }

    // ─────────────────────────────────────────────
    // SHARED RESULT ASSERTION
    // ─────────────────────────────────────────────

    @Then("leave update should be successful")
    public void verifyLeaveUpdate() {
        Assert.assertTrue(
            getLeavePage().isSuccessToastDisplayed(),
            "Leave action (approve/reject) success toast was not displayed"
        );
    }
}