package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.pages.LoginPage;
import com.skillio.utils.App;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private LoginPage login = new LoginPage(); // ✅ reuse object

    @When("user enters invalid credential")
    public void enterInvalidCredentials() {
        login.loginWithCredentials("admn", "admn234");
    }

    @Then("check if the error message appears")
    public void verifyLoginErrorMsg() {
        String actual = login.getLoginErrorMessage();
        Assert.assertEquals(actual, "Invalid credentials");
    }

    @When("user enters valid credentials")
    public void enterValidCredentials() {
        login.loginWithCredentials(
            App.getUsername("qa"),
            App.getPassword("qa")
        );
    }

    @Then("user should be redirected to the dashboard")
    public void verifyRedirectionToDashboard() {
        String header = login.getDashboardHeaderText();
        Assert.assertTrue(header.toLowerCase().contains("dashboard"));
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {
        login.loginWithCredentials(username, password);
    }

    @Then("the login result should be {string}")
    public void the_login_result_should_be(String result) {

        if ("success".equalsIgnoreCase(result)) {
            Assert.assertTrue(
                login.getDashboardHeaderText().toLowerCase().contains("dashboard")
            );
        } else {
            String err = login.getLoginErrorMessage();

            Assert.assertTrue(
                err.equalsIgnoreCase("Invalid credentials")
                || login.getUsernameFieldError().length() > 0
                || login.getPasswordFieldError().length() > 0,
                "Expected failure but no proper validation message found"
            );
        }
    }
}