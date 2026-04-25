package com.skillio.utils;

public class RecruitmentLocator {

	/** Username text box on the OrangeHRM login page */
    public static String username       = "//input[@name='username']";

    /** Password text box on the OrangeHRM login page */
   public static String password       = "//input[@name='password']";

    /** "Login" submit button */
   public static String signInButton   = "//button[@type='submit']";

    // ═══════════════════════════════════════════════════════════
    //  2. SIDEBAR NAVIGATION
    // ═══════════════════════════════════════════════════════════

    /**
     * "Recruitment" menu item in the left-hand navigation bar.
     * The sidebar uses <span> inside an <a> for each menu entry.
     */
    public static String recruitmentMenu = "//span[text()='Recruitment']";

    // ═══════════════════════════════════════════════════════════
    //  3. CANDIDATES LIST PAGE
    // ═══════════════════════════════════════════════════════════

    /** Page heading that confirms the Candidates list has loaded */
   public static String candidatesPageHeading = "//h5[normalize-space()='Candidates']";

    /** "+ Add" button in the top-right of the Candidates list */
    public static String addButton = "//button[normalize-space()='Add']";

    // ═══════════════════════════════════════════════════════════
    //  4. ADD CANDIDATE FORM
    // ═══════════════════════════════════════════════════════════

    /** Heading that confirms the Add Candidate form has opened */
   public static String addCandidateFormHeading = "//h6[normalize-space()='Add Candidate']";

    /**
     * First Name input.
     * OrangeHRM uses name="firstName" on this field.
     */
    public static String firstNameInput = "//input[@name='firstName']";

    /**
     * Last Name input.
     * OrangeHRM uses name="lastName" on this field.
     */
   public static String lastNameInput  = "//input[@name='lastName']";

    /**
     * Email field.
     * No unique name/id attribute exists; we traverse from the visible label.
     */
    public static String emailInput     = "//label[normalize-space()='Email']/following::input[1]";

    /**
     * Vacancy dropdown (optional in a full end-to-end test).
     * Useful if the form requires a vacancy to be selected before saving.
     */
   public static String vacancyDropdown       = "//label[normalize-space()='Vacancy']/following::div[contains(@class,'oxd-select-text')][1]";

    /**
     * Generic listbox option selector — append the visible text inside the XPath.
     * Usage: String.format(RecruitmentLocator.listboxOption, "Software Engineer")
     */
   public static String listboxOption  = "//div[@role='listbox']//span[normalize-space()='%s']";

    /** "Save" button at the bottom of the Add Candidate form */
  public static  String saveButton     = "//button[normalize-space()='Save']";

    // ═══════════════════════════════════════════════════════════
    //  5. VERIFICATION / POST-SAVE
    // ═══════════════════════════════════════════════════════════

    /**
     * OrangeHRM success toast notification.
     * Appears in the top-right corner after a record is saved successfully.
     */
    public static String successToast   =
        "//div[contains(@class,'oxd-toast') and .//*[contains(text(),'Successfully')]]";

    /**
     * Candidate row in the results table — use String.format() to inject the full name.
     * Usage: String.format(RecruitmentLocator.candidateRowByName, "John D")
     */
   public static String candidateRowByName =
        "//div[contains(@class,'oxd-table-body')]//div[normalize-space()='%s']";
}


