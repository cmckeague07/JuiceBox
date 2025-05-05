Feature: Login
  @OnlyThis
  Scenario: User logs in with valid credentials
    Given the user is on the login page
    When the user logs in with valid credentials
    Then the user should be redirected to the home page
    When I click the logout button
    Then I should be logged out

  Scenario Outline: Invalid login attempts are rejected
    Given the user is on the login page
    When the user attempts to log in with "<email>" and "<password>"
    Then an error message should be displayed

    Examples:
      | email             | password       |
      | invalid@email.com | wrongpass      |
      | test@juice.com    |                |
      |                   | password123    |
      | invalid@          | pass123        |


