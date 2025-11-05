@All
Feature: Basket Functionality

  Background:
    Given I am logged in as a valid user


  Scenario: Add a product to the basket via API
    Given I am logged in via API
    When I add product 1 to the basket
    Then the basket should contain product 1


  Scenario: Remove a product from the basket via API
    Given I am logged in via API
    And I navigate to the basket page
    When I manually remove the item from the basket
    Then the basket should be empty


