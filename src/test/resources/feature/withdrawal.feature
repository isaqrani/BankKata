Feature: Withdraw from account - As a client of the bank I want to withdraw money from my account In order to have cash

  Scenario: An existing client withdraws from his account
    Given an existing client named "pierre-jean" with 100 EUR in his account
    When he withdraws 10 EUR from his account
    Then his new balance is 90 EUR
