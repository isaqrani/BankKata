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
[![Build Status](https://travis-ci.org/isaqrani/BankKata.svg?branch=master)](https://travis-ci.org/isaqrani/BankKata)
[![codecov](https://codecov.io/gh/isaqrani/BankKata/branch/master/graph/badge.svg)](https://codecov.io/gh/isaqrani/BankKata)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/186a96ec67cf4011b3c0da78f0890ef5)](https://www.codacy.com/app/ilysaq/BankKata?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=isaqrani/BankKata&amp;utm_campaign=Badge_Grade)
