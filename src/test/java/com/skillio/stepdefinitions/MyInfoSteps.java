package com.skillio.stepdefinitions;

import org.testng.Assert;
import io.cucumber.java.en.*;

import pages.MyInfoPage;
import utils.DriverManager;

public class MyInfoSteps {

    private MyInfoPage myInfo = new MyInfoPage(DriverManager.getDriver());

    @When("user updates nationality {string}")
    public void updateNationality(String nationality) {
        myInfo.navigateToMyInfo();
        myInfo.selectNationality(nationality);
    }

    @Then("profile update should be {string}")
    public void verifyProfileUpdate(String result) {

        if ("success".equalsIgnoreCase(result)) {
            Assert.assertTrue(myInfo.isSuccessToastDisplayed());
        } else {
            Assert.assertTrue(myInfo.isDobErrorDisplayed());
        }
    }

    @When("user adds emergency contact {string} {string} {string}")
    public void addContact(String name, String relation, String phone) {
        myInfo.addEmergencyContact(name, relation, phone);
    }

    @Then("contact {string} should be present")
    public void verifyContact(String name) {
        Assert.assertTrue(myInfo.isEmergencyContactInList(name));
    }
}