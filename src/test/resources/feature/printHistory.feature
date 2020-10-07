Feature: Print operations history - As a client of the bank I want to see operations history

Scenario:  An existing client prints operations history

Given an existing client named "pierre-jean" with 100 EUR in his account
And he withdraws 10 EUR from his account
When he display operations history
Then his see following operations:
|Operation  | Date      | Amount| Balance|
|DEPOSIT    | 2020-10-07|    100|     100|
|WITHDRAWAL | 2020-10-07|     10|      90|