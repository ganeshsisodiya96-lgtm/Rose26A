Feature: OrangeHRM Login
  This feature contains positive, negative and data-driven scenarios for the OrangeHRM demo login page
  Background:
    Given Browser is opened and login page is launched

  Scenario: Successful login with valid credentials
    When user enters valid credentials
    Then user should be redirected to the dashboard

  Scenario: Login failure with invalid credentials
    When user enters invalid credential
    Then check if the error message appears

  Scenario Outline: Data-driven login attempts
    When user navigates to "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And user enters username "<username>" and password "<password>"
    Then the login result should be "<result>"

    Examples:
      | username        | password    | result   |
      | Admin           | admin123    | success  |
      | wronguser       | wrongpass   | failure  |
      | Admin           | wrongpass   | failure  |
      |                  |              | failure  |