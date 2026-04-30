package com.skillio.utils;

/**
 * This interface contains all the locator of the application.We are using interface because we want to achieve abstraction
 *  and we don't want to create the object of this class .We are declaring all the locators as public static final because
 *   we want to access them from anywhere in the code and we don't want to change their values.
 *   Write all the locators of the application in this interface and use them in the test 
 */



public interface Locator {

	String username = "//input[@name='username']";
	String password = "//input[@name='password']";
	String signInButton = "//button[@type='submit']";
	String Admin = "//span[text()='Admin']";
	String addButton = "//button[normalize-space()='Add']";
	String userRole = "//label[normalize-space()='User Role']/following::div[contains(@class,'oxd-select-text')][1]";
	String userRoleAdmin = "//div[@role='listbox']//span[normalize-space()='Admin']";
	String employeeName = "//input[@placeholder='Type for hints...']";
	String employeeNamemandaakhiluser = "//div[@role='listbox']//span[contains(text(),'mandaa')]";
	String status = "//label[normalize-space()='Status']/following::div[contains(@class,'oxd-select-text')]";
	String statusEnabled = "//div[@role='listbox']//span[normalize-space()='Enabled']";
	String usernameForNewUser = "//label[normalize-space()='Username']/following::input[1]";
	String passwordForNewUser = "//label[normalize-space()='Password']/following::input[1]";
	String confirmPasswordForNewUser = "//label[normalize-space()='Confirm Password']/following::input[1]";
	String saveButton = "//button[normalize-space()='Save']";

	
	
	
}
