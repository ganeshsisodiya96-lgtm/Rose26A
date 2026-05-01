package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.pages.LoginPage;

import io.cucumber.java.en.*;

public class LoginSteps {

    LoginPage login = new LoginPage();

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        login.enterUserName("Admin");
        login.enterPassword("admin123");
        login.clickSignInBtn();
    }

    @Then("user should be redirected to the dashboard")
    public void user_should_be_redirected_to_the_dashboard() {
        Assert.assertTrue(
            login.getDashboardHeaderText().toLowerCase().contains("dashboard"),
            "Expected dashboard after successful login");
    }

    @When("user enters invalid credential")
    public void user_enters_invalid_credential() {
        login.enterUserName("wrongUser");
        login.enterPassword("wrongPass");
        login.clickSignInBtn();
    }

    @Then("check if the error message appears")
    public void check_if_the_error_message_appears() {
        Assert.assertTrue(login.isLoginErrorVisible(), "Login error not shown");
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        login.enterUserName(username);
        login.enterPassword(password);
        login.clickSignInBtn();
    }

    @Then("the login result should be {string}")
    public void the_login_result_should_be(String result) {
        if ("success".equalsIgnoreCase(result)) {
            Assert.assertTrue(
                login.getDashboardHeaderText().toLowerCase().contains("dashboard"),
                "Expected dashboard after successful login");
        } else {
            // For both empty credentials and wrong credentials,
            // the user stays on the login page (not redirected to dashboard)
            boolean onDashboard = false;
            try {
                String header = login.getDashboardHeaderText();
                onDashboard = header.toLowerCase().contains("dashboard");
            } catch (Exception ignored) {}

            Assert.assertFalse(onDashboard,
                "Expected login to fail but user was redirected to dashboard");
        }
    }
}