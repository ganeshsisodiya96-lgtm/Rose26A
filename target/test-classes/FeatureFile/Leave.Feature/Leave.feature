Feature: Leave - Apply and Manage Leave

  # TC_LEAVE_01 - Positive: Employee applies for leave (role: Employee)
  Scenario: TC_LEAVE_01 - Employee can apply for annual leave
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to Leave module
    And user clicks "Apply" option
    And user selects leave type "Annual Leave"
    And user enters from date "2025-08-01" and to date "2025-08-03"
    And user enters comment "Family vacation"
    And user clicks Apply button
    Then leave request should be submitted with status "Pending Approval"

  # TC_LEAVE_02 - Negative: Leave application with from date after to date
  Scenario: TC_LEAVE_02 - System rejects leave with invalid date range
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to Leave module
    And user clicks "Apply" option
    And user selects leave type "Annual Leave"
    And user enters from date "2025-08-10" and to date "2025-08-05"
    And user clicks Apply button
    Then leave date error "To date should be after from date" should be displayed

  # TC_LEAVE_03 - Positive: Admin approves a pending leave request
  Scenario: TC_LEAVE_03 - Admin can approve employee leave request
    Given user is logged into OrangeHRM as "Admin"
    When user navigates to Leave module
    And user clicks "Leave List" option
    And user filters by status "Pending Approval"
    And user opens first pending leave request
    And user clicks Approve button
    Then leave status should change to "Approved"

  # TC_LEAVE_04 - Positive: Admin rejects a pending leave request
  Scenario: TC_LEAVE_04 - Admin can reject employee leave request
    Given user is logged into OrangeHRM as "Admin"
    When user navigates to Leave module
    And user clicks "Leave List" option
    And user filters by status "Pending Approval"
    And user opens first pending leave request
    And user clicks Reject button
    Then leave status should change to "Rejected"

  # TC_LEAVE_05 - Boundary: Apply leave for a single day
  Scenario: TC_LEAVE_05 - Employee applies for single-day leave
    Given user is logged into OrangeHRM as "Employee"
    When user navigates to Leave module
    And user clicks "Apply" option
    And user selects leave type "Sick Leave"
    And user enters from date "2025-09-01" and to date "2025-09-01"
    And user clicks Apply button
    Then leave request should be submitted with status "Pending Approval"


