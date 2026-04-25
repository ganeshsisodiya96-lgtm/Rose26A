Feature: Recruitment - Job Vacancy and Application Management

  Background:
    Given user is logged into OrangeHRM as "Admin"

  # TC_REC_01 - Positive: Add a new job vacancy
  Scenario: TC_REC_01 - Admin can add a new job vacancy
    When user navigates to Recruitment module
    And user clicks on "Add" button in Vacancies section
    And user enters vacancy name "QA Engineer"
    And user selects job title "Software Engineer"
    And user enters number of positions "2"
    And user clicks Save button
    Then vacancy "QA Engineer" should appear in the vacancies list

  # TC_REC_02 - Negative: Add vacancy with missing job title
  Scenario: TC_REC_02 - Vacancy creation fails without job title
    When user navigates to Recruitment module
    And user clicks on "Add" button in Vacancies section
    And user enters vacancy name "DevOps Engineer"
    And user leaves job title blank
    And user clicks Save button
    Then field validation error "Required" should appear under job title field

  # TC_REC_03 - Positive: Add a new candidate application
  Scenario: TC_REC_03 - Admin can add candidate for a vacancy
    When user navigates to Recruitment module
    And user opens vacancy "QA Engineer"
    And user clicks "Add" in candidates section
    And user enters candidate first name "Alice" and last name "Walker"
    And user enters candidate email "alice.walker@test.com"
    And user clicks Save button
    Then candidate "Alice Walker" should be added under "QA Engineer" vacancy

  # TC_REC_04 - Negative: Candidate email with invalid format is rejected
  Scenario: TC_REC_04 - System rejects invalid candidate email
    When user navigates to Recruitment module
    And user opens vacancy "QA Engineer"
    And user clicks "Add" in candidates section
    And user enters candidate first name "Bob" and last name "Taylor"
    And user enters candidate email "invalid-email"
    And user clicks Save button
    Then field validation error "Expected format: admin@example.com" should appear

  # TC_REC_05 - Positive: Search candidates by vacancy name
  Scenario: TC_REC_05 - Admin can search candidates by vacancy
    When user navigates to Recruitment module
    And user selects vacancy "QA Engineer" in candidate search form
    And user clicks Search button
    Then candidates list for "QA Engineer" should be displayed

