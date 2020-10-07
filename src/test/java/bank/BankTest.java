package bank;

import bank.account.Account;
import bank.account.Client;
import bank.transaction.Transaction;
import bank.transaction.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {

    private static final int INITIAL_AMOUNT = 100;
    private Bank bank;
    @Mock
    private Client client;
    @Mock
    private Account account;

    @Before
    public void initialise() {
        bank = new Bank();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(TransactionType.DEPOSIT, INITIAL_AMOUNT));
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, 50));
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, 30));
        transactions.add(new Transaction(TransactionType.DEPOSIT, 100));
        transactions.add(new Transaction(TransactionType.DEPOSIT, 150));
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, 200));

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

}
