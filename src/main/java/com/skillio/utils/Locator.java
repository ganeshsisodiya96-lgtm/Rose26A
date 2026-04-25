package com.skillio.utils;

/**
 * Centralized Locator Interface
 * Contains all application locators
 */
public interface Locator {

    // ─────────────────────────────────────────────
    // LOGIN MODULE
    // ─────────────────────────────────────────────
    String LOGIN_USERNAME  = "//input[@name='username']";
    String LOGIN_PASSWORD  = "//input[@name='password']";
    String LOGIN_BUTTON    = "//button[@type='submit']";

    // ─────────────────────────────────────────────
    // ADMIN MODULE
    // ─────────────────────────────────────────────
    String ADMIN_MENU      = "//span[text()='Admin']";
    String ADD_BUTTON      = "//button[normalize-space()='Add']";

    // ─────────────────────────────────────────────
    // USER ROLE
    // ─────────────────────────────────────────────
    String USER_ROLE_DROPDOWN =
        "//label[normalize-space()='User Role']/following::div[contains(@class,'oxd-select-text')][1]";

    String USER_ROLE_ADMIN =
        "//div[@role='listbox']//span[normalize-space()='Admin']";

    // ─────────────────────────────────────────────
    // EMPLOYEE
    // ─────────────────────────────────────────────
    String EMPLOYEE_NAME_INPUT =
        "//input[@placeholder='Type for hints...']";

    String EMPLOYEE_NAME_OPTION =
        "//div[@role='listbox']//span[contains(text(),'mandaa')]";

    // ─────────────────────────────────────────────
    // STATUS
    // ─────────────────────────────────────────────
    String STATUS_DROPDOWN =
        "//label[normalize-space()='Status']/following::div[contains(@class,'oxd-select-text')]";

    String STATUS_ENABLED =
        "//div[@role='listbox']//span[normalize-space()='Enabled']";

    // ─────────────────────────────────────────────
    // USER CREATION
    // ─────────────────────────────────────────────
    String NEW_USERNAME =
        "//label[normalize-space()='Username']/following::input[1]";

    String NEW_PASSWORD =
        "//label[normalize-space()='Password']/following::input[1]";

    String CONFIRM_PASSWORD =
        "//label[normalize-space()='Confirm Password']/following::input[1]";

    String SAVE_BUTTON =
        "//button[normalize-space()='Save']";
}