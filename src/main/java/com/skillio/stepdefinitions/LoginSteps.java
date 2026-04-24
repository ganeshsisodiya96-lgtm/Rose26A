package com.skillio.stepdefinitions;

import static com.skillio.base.Keyword.launchUrl;

import static com.skillio.base.Keyword.openBrowser;

import org.testng.Assert;

import com.skillio.pages.LoginPage;
import com.skillio.utils.App;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	@Given("Browser is opened and login page is launched")
	public void openBrowserAndLaunchUrl() {
		openBrowser(App.getBrowserName()); 
		launchUrl(App.getappUrl("qa"));


	}
	@When("user enters invalid credential")
	public void enterInvalidCredentials() {
		LoginPage login = new LoginPage();
		login.enterUserName("admn");
		login.enterPassword("admn234");
		login.clickSignInBtn();

	}
	@Then("check if the error message appears")
	public void verifyLoginErrorMsg() {
		LoginPage login =new LoginPage();
		String expMsg="Invalid credentials";
		String actualErroMsg =login.getLoginErrorMessage();
		Assert.assertEquals(actualErroMsg, expMsg, "Error message is invalid: "+actualErroMsg);

	}
	
	
	// New step: enter valid credentials
	@When("user enters valid credentials")
	public void enterValidCredentials() {
		LoginPage login = new LoginPage();
		String username = App.getUsername("qa");
		String password = App.getPassword("qa");
		login.loginWithCredentials(username, password);
	}

	// New step: verify redirection to dashboard
	@Then("user should be redirected to the dashboard")
	public void verifyRedirectionToDashboard() {
		LoginPage login = new LoginPage();
		String header = login.getDashboardHeaderText();
		Assert.assertTrue(header != null && header.toLowerCase().contains("dashboard"), "Should land on Dashboard. Actual header: " + header);
	}

	// New step: navigate to a specific URL
	@When("user navigates to {string}")
	public void user_navigates_to(String url) {
		launchUrl(url);
	}

	// New step: parameterized login
	@When("user enters username {string} and password {string}")
	public void user_enters_username_and_password(String username, String password) {
		LoginPage login = new LoginPage();
		login.loginWithCredentials(username, password);
	}

	// New step: check login result (success/failure)
	@Then("the login result should be {string}")
	public void the_login_result_should_be(String result) {
		LoginPage login = new LoginPage();
		if ("success".equalsIgnoreCase(result)) {
			String header = login.getDashboardHeaderText();
			Assert.assertTrue(header != null && header.toLowerCase().contains("dashboard"), "Expected successful login but dashboard not visible. Actual: " + header);
		} else {
			String err = login.getLoginErrorMessage();
			String userFieldErr = login.getUsernameFieldError();
			String pwdFieldErr = login.getPasswordFieldError();
			// At least one indication of failure should be present
			boolean failureDetected = (err != null && !err.isEmpty()) || (userFieldErr != null && !userFieldErr.isEmpty()) || (pwdFieldErr != null && !pwdFieldErr.isEmpty());
			Assert.assertTrue(failureDetected, "Expected login failure but no error or validation messages were found.");
		}
	}

}