package feature;

import bank.Bank;
import bank.infra.Console;
import bank.history.Operation;
import bank.history.OperationsHistoryPrinter;
import bank.account.Account;
import bank.account.Client;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class WithdrawStepDefinitions {

    private final Console console = Mockito.mock(Console.class);
    private final OperationsHistoryPrinter printer = new OperationsHistoryPrinter(console);
    private final Bank bank = new Bank(() -> LocalDate.of(2020, 10, 7), printer);
    private Account account;

    @Given("^an existing client named \"([^\"]*)\" with (\\d+) EUR in his account$")
    public void an_existing_client_named_name_with_balance_EUR_in_his_account(String name, int balance) throws Throwable {
        account = bank.createAccount(Client.builder().name(name).build(), balance);
    }

    @When("^he withdraws (\\d+) EUR from his account$")
    public void he_withdraws_amount_EUR_from_his_account(int amount){
        bank.withdraw(account, amount);
    }

    @When("^he deposits (\\d+) EUR into his account$")
    public void he_into_amount_EUR_into_his_account(int amount){
        bank.deposit(account, amount);
    }
    
    @Then("^his new balance is (\\d+) EUR$")
    public void the_new_balance_is_balance_EUR(int balance){
        assertThat(bank.calculateBalance(account)).isEqualTo(balance);
    }

    @When("^he display operations history$")
    public void heDisplayOperationsHistory() {
        bank.printHistory(account);
    }

    @Then("^his see following operations:$")
    public void hisSeeFollowingOperations(List<Operation> operations) {
        verify(console).print(operations);
    }
}