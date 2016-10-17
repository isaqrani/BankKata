package bank;

import bank.account.Account;
import bank.account.Client;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {

    private static final int INITIAL_AMOUNT = 100;
    private Bank bank;
    @Mock
    private Client client;

    @Before
    public void initialise() {
        bank = new Bank();
    }

    @Test public void
    should_create_a_new_account() {
        bank.createAccount(client, 0);
        assertEquals(1,bank.getAccounts().size());
    }

    @Test public void
    should_create_a_new_account_with_initial_balance() {
        Account account = bank.createAccount(client, INITIAL_AMOUNT);
        assertEquals(1,bank.getAccounts().size());
        assertEquals(INITIAL_AMOUNT,bank.calculateBalance(account));
    }

    @Test
    public void
    should_calculate_balance_after_withdrawals() {
        Account account = bank.createAccount(client, INITIAL_AMOUNT);
        bank.withdraw(account,50);
        bank.withdraw(account,30);
        assertEquals(20,bank.calculateBalance(account));
    }

}
