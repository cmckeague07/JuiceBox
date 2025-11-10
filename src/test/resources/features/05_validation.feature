@All
Feature: Form and checkout validation scenarios

  Background:
    Given I am logged in as a valid user

  @validation @requiredField
  Scenario: Required field validation in the address form
    Given the user is on the address creation page
    When the user leaves the required "country" field blank
    Then the "Submit" button should remain disabled
    And the address form should stay open

  @validation @basketPersistence
  Scenario: Basket contents persist after page refresh during checkout
    Given the user adds an item to the basket
    And navigates to the checkout page
    When the user refreshes the page
    Then the basket should still contain the previously added item
    And the basket is cleared down

