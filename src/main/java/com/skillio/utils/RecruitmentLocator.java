package com.skillio.utils;

public class RecruitmentLocator {

    // ═══════════════════════════════════════
    // LOGIN PAGE
    // ═══════════════════════════════════════
    public static String username = "//input[@name='username']";
    public static String password = "//input[@name='password']";

    // 🔥 More stable than type='submit'
    public static String signInButton = "//button[.//text()='Login']";

    // ═══════════════════════════════════════
    // SIDEBAR
    // ═══════════════════════════════════════

    // 🔥 Avoid exact match
    public static String recruitmentMenu = "//span[contains(text(),'Recruitment')]";

    // ═══════════════════════════════════════
    // CANDIDATE PAGE
    // ═══════════════════════════════════════
    public static String candidatesPageHeading = "//h5[normalize-space()='Candidates']";

    // 🔥 Scoped button (inside header)
    public static String addButton = "//h5[normalize-space()='Candidates']/following::button[normalize-space()='Add'][1]";

    // ═══════════════════════════════════════
    // ADD CANDIDATE FORM
    // ═══════════════════════════════════════
    public static String addCandidateFormHeading = "//h6[normalize-space()='Add Candidate']";

    public static String firstNameInput = "//input[@name='firstName']";
    public static String lastNameInput  = "//input[@name='lastName']";

    // 🔥 More stable relative locator
    public static String emailInput =
        "//label[normalize-space()='Email']/ancestor::div[contains(@class,'oxd-input-group')]//input";

    // 🔥 Dropdown improved
    public static String vacancyDropdown =
        "//label[normalize-space()='Vacancy']/ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]";

    public static String listboxOption =
        "//div[@role='listbox']//span[normalize-space()='%s']";

    public static String saveButton = "//button[normalize-space()='Save']";

    // ═══════════════════════════════════════
    // VERIFICATION
    // ═══════════════════════════════════════

    public static String successToast =
        "//div[contains(@class,'oxd-toast')]//p[contains(text(),'Successfully')]";

    // 🔥 Scoped table locator (important fix)
    public static String candidateRowByName =
        "//div[contains(@class,'oxd-table-body')]//div[@role='row']//div[normalize-space()='%s']";
}