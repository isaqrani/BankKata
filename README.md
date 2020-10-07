# BankKata
Features:
```
Feature: Withdraw from account
  As a client of the bank
  I want to withdraw money from my account
  In order to have cash
```
```
Feature: Deposit into account
  As a client of the bank
  I want to make a deposit in my account
```
The goal of this kata is to write and make the following acceptance test pass using BDD and TDD approaches: 
```
Scenario: An existing client withdraws from his account

  Given an existing client named "pierre-jean" with 100.0 EUR in his account

  When he withdraws 10.0 EUR from his account
  
  Then the new balance is 90.0 EUR 
```
```
Scenario:  An existing client deposits into his account
   
   Given an existing client named "pierre-jean" with 100.0 EUR in his account
   
   When he deposits 250.0 EUR into his account
   
   Then his new balance is 350.0 EUR
```