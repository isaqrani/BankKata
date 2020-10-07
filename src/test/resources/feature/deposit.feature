Feature: Deposit in account - As a client of the bank I want to deposit money in my account In order to have cash

  Scenario: An existing client deposit from his account
    Given an existing client named "pierre-jean" with 100 EUR in his account
    When he deposits 100 EUR into his account
    Then his new balance is 200 EUR