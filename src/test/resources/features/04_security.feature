@All
Feature: Security and session management validation
  As a QA engineer
  I want to verify key security behaviours in the Juice Shop application
  So that unauthorized access, session handling, and injection attempts are properly secured

  Background:
    Given the user opens the Juice Shop application
    And the browser is cleared of all cookies and sessions

  @security @session
  Scenario: Verify user session persists after refresh
    Given the user logs in with valid credentials for security testing
    When the user refreshes the page
    Then the user should remain logged in

  @security
  Scenario: Redirect unauthenticated user to login page
    Given the user is not logged in
    When the user tries to access the checkout page directly via URL
    Then display a message indicating authentication is required
    Then the application should redirect them to the login page


  @security @headers
  Scenario: Validate core security headers are present, 1.0
    When the user sends a GET request to the home page
    Then the response headers should include "Content-Security-Policy"

  @security @headers
  Scenario: Validate core security headers are present, 2.0
    When the user sends a GET request to the home page
    And the headers should include "X-Frame-Options"

  @security @headers
  Scenario: Validate core security headers are present, 3.0
    When the user sends a GET request to the home page
    And the headers should include "X-Content-Type-Options"

  @security @sanitization
  Scenario: Prevent script injection in search field
    Given the user is on the main shop page
    When the user searches for "<script>alert('xss')</script>"
    Then no alert dialog should appear
    And the search results should safely render escaped text instead of executing the script
