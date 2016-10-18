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

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {

    private static final int INITIAL_AMOUNT = 100;
    private Bank bank;
    private List<Transaction> transactions;
    @Mock
    private Client client;
    @Mock
    private Account account;

    @Before
    public void initialise() {
        bank = new Bank();

        transactions = new ArrayList<>();
        transactions.add(new Transaction(TransactionType.DEPOSIT, INITIAL_AMOUNT));
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, 50));
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, 30));

        given(account.getTransactions()).willReturn(transactions);
    }

    @Test
    public void
    should_create_a_new_account() {
        bank.createAccount(client, 0);
        assertEquals(1, bank.getAccounts().size());
    }

    @Test
    public void
    should_create_a_new_account_with_initial_balance() {
        Account account = bank.createAccount(client, INITIAL_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bank.calculateBalance(account));
    }

    @Test
    public void
    should_calculate_balance_after_withdrawals() {
        assertEquals(20, bank.calculateBalance(account));
    }

}
