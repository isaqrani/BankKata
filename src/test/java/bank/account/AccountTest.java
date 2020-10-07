package bank.account;

import bank.transaction.Transaction;
import bank.transaction.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;

public class AccountTest {

    @Mock
    private Client client;
    private Account account;

    @Before
    public void initialise() {
        account = new Account(client);
    }

    @Test
    public void should_store_withdrawal() {
        account.addTransaction(new Transaction(TransactionType.WITHDRAWAL, 100));
        assertThat(account.getTransactions()).hasSize(1);
    }

}
