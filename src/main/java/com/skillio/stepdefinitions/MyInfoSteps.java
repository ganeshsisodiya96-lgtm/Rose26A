package com.skillio.stepdefinitions;

import org.testng.Assert;

import com.skillio.pages.MyInfoPage;

import io.cucumber.java.en.*;

public class MyInfoSteps {

    private MyInfoPage myInfo = new MyInfoPage();

    @When("user navigates to My Info module")
    public void userNavigatesToMyInfoModule() {
        myInfo.navigateToMyInfo();
    }

    @When("user clicks on Personal Details tab")
    public void userClicksOnPersonalDetailsTab() {
        myInfo.clickPersonalDetailsTab();
    }

    @When("user clicks on Contact Details tab")
    public void userClicksOnContactDetailsTab() {
        myInfo.clickContactDetailsTab();
    }

    @When("user clicks on Emergency Contacts tab")
    public void userClicksOnEmergencyContactsTab() {
        myInfo.clickEmergencyContactsTab();
    }

    @When("user selects nationality {string}")
    public void userSelectsNationality(String nationality) {
        myInfo.selectNationality(nationality);
    }

    @When("user enters street1 {string}")
    public void userEntersStreet1(String street) {
        myInfo.enterStreet1(street);
    }

    @When("user enters city {string}")
    public void userEntersCity(String city) {
        myInfo.enterCity(city);
    }

    @When("user enters date of birth {string}")
    public void userEntersDateOfBirth(String dob) {
        myInfo.enterDob(dob);
    }

    @When("user clicks Add button")
    public void userClicksAddButton() {
        myInfo.clickAdd();
    }

    @When("user enters contact name {string}")
    public void userEntersContactName(String name) {
        myInfo.enterContactName(name);
    }

    @When("user enters relationship {string}")
    public void userEntersRelationship(String relationship) {
        myInfo.enterRelationship(relationship);
    }

    @When("user enters home phone {string}")
    public void userEntersHomePhone(String phone) {
        myInfo.enterHomePhone(phone);
    }

    @When("user enters first name with 30 characters {string}")
    public void userEntersFirstNameWithMaxChars(String name) {
        myInfo.enterFirstName(name);
    }

    @Then("nationality should be saved as {string}")
    public void nationalityShouldBeSavedAs(String nationality) {
        Assert.assertTrue(myInfo.isSuccessToastDisplayed(),
            "Expected nationality '" + nationality + "' to be saved");
    }

    @Then("field validation error should appear under date of birth")
    public void fieldValidationErrorUnderDob() {
        Assert.assertTrue(myInfo.isDobErrorDisplayed(), "DOB error not shown");
    }

    @Then("emergency contact {string} should be listed")
    public void emergencyContactShouldBeListed(String name) {
        Assert.assertTrue(myInfo.isEmergencyContactInList(name),
            "Emergency contact '" + name + "' not found");
    }

    @Then("the name should be saved successfully without any error")
    public void theNameShouldBeSavedSuccessfully() {
        Assert.assertTrue(myInfo.isSuccessToastDisplayed(), "Name not saved");
    }
}
