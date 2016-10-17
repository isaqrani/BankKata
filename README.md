# BankKata
```
Feature: Withdraw from account
  As a client of the bank
  I want to withdraw money from my account
  In order to have cash
```
The goal of this kata is to write and make the following acceptance test pass using BDD and TDD approaches: 
```
Scenario: An existing client withdraws from his account

  Given an existing client named "pierre-jean" with 100.0 EUR in his account

  When he withdraws 10.0 EUR from his account
  
  Then the new balance is 90.0 EUR 
```
[![Build Status](https://travis-ci.org/isaqrani/BankKata.svg?branch=ci_branche)](https://travis-ci.org/isaqrani/BankKata)
[![codecov](https://codecov.io/gh/isaqrani/Bank-kata/branch/master/graph/badge.svg)](https://codecov.io/gh/isaqrani/BankKata)
