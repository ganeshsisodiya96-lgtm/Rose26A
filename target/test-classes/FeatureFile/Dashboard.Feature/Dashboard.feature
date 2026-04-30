Feature: OrangeHRM Dashboard Functionality

  Background:
    Given user is logged into OrangeHRM

  Scenario: Verify Dashboard page header
    When user navigates to the Dashboard
    Then the page header should display "Dashboard"

  Scenario: Verify Quick Launch widget visibility
    When user navigates to the Dashboard
    Then the "Quick Launch" widget should be visible on the dashboard

  Scenario: Verify side menu search filters modules
    When user types "Admin" into the side menu search bar
    Then only the Admin module should be visible in the menu