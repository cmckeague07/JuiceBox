@All
Feature: Accessibility & Performance Audit Tracking

  Scenario: Confirm Lighthouse audit was executed
    Given the accessibility and performance audits were run externally
    Then the generated reports should exist in the reports lighthouse folder
