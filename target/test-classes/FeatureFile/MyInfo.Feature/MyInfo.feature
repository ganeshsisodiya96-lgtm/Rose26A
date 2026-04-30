Feature: My Info - Personal Information Management

  # TC_MYINFO_01 - Positive: Employee updates personal details
  Scenario: TC_MYINFO_01 - Employee can update their nationality
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to My Info module
    And user clicks on Personal Details tab
    And user selects nationality "Indian"
    And user clicks Save button
    Then success toast message should be displayed
    And nationality should be saved as "Indian"

  # TC_MYINFO_02 - Positive: Employee updates contact details
  Scenario: TC_MYINFO_02 - Employee can update contact information
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to My Info module
    And user clicks on Contact Details tab
    And user enters street1 "123 Main Street"
    And user enters city "Mumbai"
    And user clicks Save button
    Then success toast message should be displayed

  # TC_MYINFO_03 - Negative: Invalid date format in date of birth field
  Scenario: TC_MYINFO_03 - System rejects invalid date format in DOB field
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to My Info module
    And user clicks on Personal Details tab
    And user enters date of birth "32/13/2000"
    And user clicks Save button
    Then field validation error should appear under date of birth

  # TC_MYINFO_04 - Positive: Employee can add emergency contact
  Scenario: TC_MYINFO_04 - Employee adds an emergency contact
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to My Info module
    And user clicks on Emergency Contacts tab
    And user clicks Add button
    And user enters contact name "Ravi Kumar"
    And user enters relationship "Brother"
    And user enters home phone "9876543210"
    And user clicks Save button
    Then emergency contact "Ravi Kumar" should be listed

  # TC_MYINFO_05 - Boundary: Employee name field with maximum character limit
  Scenario: TC_MYINFO_05 - First name field respects maximum character boundary
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to My Info module
    And user clicks on Personal Details tab
    And user enters first name with 30 characters "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    And user clicks Save button
    Then the name should be saved successfully without any error

