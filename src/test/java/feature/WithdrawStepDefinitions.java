package feature;

import bank.Bank;
import bank.account.Account;
import bank.account.Client;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertEquals;

public class WithdrawStepDefinitions {

    private Bank bank = new Bank();
    private Account account;

    @Given("^an existing client named \"([^\"]*)\" with (\\d+) EUR in his account$")
    public void an_existing_client_named_name_with_balance_EUR_in_his_account(String name, int balance) throws Throwable {
        account = bank.createAccount(new Client(name), balance);
    }

    @When("^he withdraws (\\d+) EUR from his account$")
    public void he_withdraws_amount_EUR_from_his_account(int amount) throws Throwable {
        bank.withdraw(account, amount);
    }

    @When("^he deposits (\\d+) EUR into his account$")
    public void he_into_amount_EUR_into_his_account(int amount) throws Throwable {
        bank.deposit(account, amount);
    }
    
    @Then("^his new balance is (\\d+) EUR$")
    public void the_new_balance_is_balance_EUR(int balance) throws Throwable {
        assertEquals(balance, bank.calculateBalance(account));
    } 

}