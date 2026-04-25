package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.pages.DashboardPage;
import com.skillio.pages.LoginPage;

import io.cucumber.java.en.*;

public class DashboardSteps {

    DashboardPage dashboardPage;
    LoginPage loginPage;

    public DashboardSteps() {
        dashboardPage = new DashboardPage();
        loginPage = new LoginPage();
    }

    @Given("user is logged into OrangeHRM")
    public void userIsLoggedIntoOrangeHRM() {
        loginPage.enterUserName("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickSignInBtn();
    }

    @When("user navigates to the Dashboard")
    public void userNavigatesToTheDashboard() {
        // Optional (usually lands on dashboard after login)
    }

    @Then("the page header should display {string}")
    public void thePageHeaderShouldDisplay(String expectedHeader) {
        Assert.assertEquals(dashboardPage.getPageHeader(), expectedHeader);
    }

    @Then("the {string} widget should be visible on the dashboard")
    public void theWidgetShouldBeVisible(String widgetName) {
        Assert.assertTrue(dashboardPage.isWidgetDisplayed(widgetName));
    }

    @When("user types {string} into the side menu search bar")
    public void userTypesIntoSideMenuSearch(String searchText) {
        dashboardPage.searchMenu(searchText);
    }

    @Then("only the Admin module should be visible in the menu")
    public void onlyTheAdminModuleShouldBeVisible() {
        Assert.assertTrue(dashboardPage.isAdminMenuVisible());
    }
}