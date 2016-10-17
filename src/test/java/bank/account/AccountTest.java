package bank.account;

import bank.transaction.Transaction;
import bank.transaction.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Mock
    Client client;
    private Account account;

    @Before
    public void initialise() {
        account = new Account(client);
    }

    @Test
    public void should_store_withdrawal() {
        account.addTransaction(new Transaction(TransactionType.WITHDRAWAL, 100));
        assertEquals(1, account.getTransactions().size());
    }

}
