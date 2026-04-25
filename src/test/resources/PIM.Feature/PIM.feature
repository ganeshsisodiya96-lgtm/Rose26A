Feature: PIM - Employee Management

  Background:
    Given user is logged into OrangeHRM as "Admin"

  # TC_PIM_01 - Positive: Add new employee
  Scenario: TC_PIM_01 - Admin can add a new employee with mandatory fields
    When user navigates to PIM module
    And user clicks on "Add Employee" button
    And user enters first name "John" and last name "Doe"
    And user enters employee ID "EMP001"
    And user clicks Save button
    Then employee "John Doe" should be successfully created
    And success toast message should be displayed

  # TC_PIM_02 - Negative: Add employee with duplicate Employee ID
  Scenario: TC_PIM_02 - System rejects duplicate employee ID
    When user navigates to PIM module
    And user clicks on "Add Employee" button
    And user enters first name "Jane" and last name "Smith"
    And user enters employee ID "EMP001"
    And user clicks Save button
    Then error message "Employee Id already exists" should be displayed

  # TC_PIM_03 - Negative: Add employee with empty mandatory fields
  Scenario: TC_PIM_03 - Validation triggers when mandatory fields are empty
    When user navigates to PIM module
    And user clicks on "Add Employee" button
    And user clears first name field
    And user clears last name field
    And user clicks Save button
    Then field validation error "Required" should appear under first name
    And field validation error "Required" should appear under last name

  # TC_PIM_04 - Positive: Search employee by name
  Scenario: TC_PIM_04 - Admin can search for an existing employee
    When user navigates to PIM module
    And user enters employee name "John Doe" in search box
    And user clicks Search button
    Then employee "John Doe" should appear in search results

  # TC_PIM_05 - Positive: Delete an existing employee
  Scenario: TC_PIM_05 - Admin can delete an employee record
    When user navigates to PIM module
    And user searches for employee "John Doe"
    And user selects employee checkbox
    And user clicks Delete Selected button
    And user confirms deletion in the dialog
    Then employee 
