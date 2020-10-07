package bank;

import bank.account.Account;
import bank.account.Client;
import bank.history.OperationsHistoryPrinter;
import bank.infra.Clock;
import bank.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static bank.transaction.TransactionType.DEPOSIT;
import static bank.transaction.TransactionType.WITHDRAWAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {

    private static final int INITIAL_AMOUNT = 100;
    private Bank bank;
    @Mock
    private Client client;
    @Mock
    private Account account;
    @Mock
    private Clock clock;
    @Mock
    private OperationsHistoryPrinter operationsHistoryPrinter;

    @Before
    public void initialise() {
        bank = new Bank(clock, operationsHistoryPrinter);

        List<Transaction> transactions = new ArrayList<>();
        doReturn(LocalDate.of(2020,10,7)).when(clock).now();
        transactions.add(Transaction.builder().type(DEPOSIT).amount(INITIAL_AMOUNT).date(clock.now()).build());
        doReturn(LocalDate.of(2020,10,8)).when(clock).now();
        transactions.add(Transaction.builder().type(WITHDRAWAL).amount(50).date(clock.now()).build());

        doReturn(LocalDate.of(2020,10,9)).when(clock).now();
        transactions.add(Transaction.builder().type(WITHDRAWAL).amount(30).date(clock.now()).build());
        doReturn(LocalDate.of(2020,10,24)).when(clock).now();
        transactions.add(Transaction.builder().type(DEPOSIT).amount(100).date(clock.now()).build());
        doReturn(LocalDate.of(2020,10,24)).when(clock).now();
        transactions.add(Transaction.builder().type(DEPOSIT).amount(150).date(clock.now()).build());
        doReturn(LocalDate.of(2020,11,7)).when(clock).now();
        transactions.add(Transaction.builder().type(WITHDRAWAL).amount(200).date(clock.now()).build());

        given(account.getTransactions()).willReturn(transactions);
    }

    @Test
    public void
    should_create_a_new_account() {
        bank.createAccount(client, 0);
        assertThat(bank.getAccounts()).hasSize(1);
    }

    @Test
    public void
    should_create_a_new_account_with_initial_balance() {
        Account account = bank.createAccount(client, INITIAL_AMOUNT);
        assertThat(INITIAL_AMOUNT).isEqualTo(bank.calculateBalance(account));
    }

    @Test
    public void
    should_calculate_balance_after_one_withdrawal() {
        Account account = bank.createAccount(client, INITIAL_AMOUNT);
        bank.withdraw(account, 60);
        assertThat(bank.calculateBalance(account)).isEqualTo(40);
    }
    
    @Test
    public void
    should_calculate_balance_after_one_deposit() {
        Account account = bank.createAccount(client, INITIAL_AMOUNT);
        bank.deposit(account, 60);
        assertThat(bank.calculateBalance(account)).isEqualTo(160);
    }

    @Test
    public void
    should_calculate_balance_after_transactions() {
        assertThat(bank.calculateBalance(account)).isEqualTo(70);
    }

    @Test
    public void
    should_print_history() {
        doReturn(LocalDate.of(2020,10,8)).when(clock).now();
        List<Transaction> expectedTransactions = Arrays.asList(
        Transaction.builder().type(DEPOSIT).amount(100).date(clock.now()).build(),
        Transaction.builder().type(DEPOSIT).amount(60).date(clock.now()).build(),
        Transaction.builder().type(DEPOSIT).amount(100).date(clock.now()).build(),
        Transaction.builder().type(WITHDRAWAL).amount(60).date(clock.now()).build());

        Account account = bank.createAccount(client, INITIAL_AMOUNT);
        bank.deposit(account, 60);
        bank.deposit(account, 100);
        bank.withdraw(account, 60);

        bank.printHistory(account);

        verify(operationsHistoryPrinter).printHistory(expectedTransactions);
    }
}
