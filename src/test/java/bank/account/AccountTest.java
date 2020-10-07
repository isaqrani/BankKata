package bank.account;

import bank.transaction.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDate;

import static bank.transaction.TransactionType.WITHDRAWAL;
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
        LocalDate date = LocalDate.of(2020,10,7);
        account.addTransaction(Transaction.builder().type(WITHDRAWAL).amount(100).date(date).build());
        assertThat(account.getTransactions()).hasSize(1);
    }

}
