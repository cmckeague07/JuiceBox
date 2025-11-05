@All
Feature: Checkout Functionality

  As a customer
  I want to complete checkout with valid and invalid inputs
  So that I can verify the end-to-end purchase flow


  Background:
    Given I am logged in as a valid user
    And I have added an item to the basket

  Scenario: Successful checkout with new address and payment method
    When I open the basket
    And I proceed to checkout
    And I add a new address with valid details
    And I select the newly added address
    And I choose "Fast Delivery" as my delivery option
    And I add a new payment method with valid details
    And I select the newly added payment method
    And I place the order
    Then I should see an order confirmation page
    And I should be able to download the order confirmation
