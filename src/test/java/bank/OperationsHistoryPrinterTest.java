package bank;

import bank.history.Operation;
import bank.history.OperationsHistoryPrinter;
import bank.infra.Console;
import bank.transaction.Transaction;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static bank.transaction.TransactionType.DEPOSIT;
import static bank.transaction.TransactionType.WITHDRAWAL;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OperationsHistoryPrinterTest extends TestCase {

    @Mock
    private Console console;
    @InjectMocks
    OperationsHistoryPrinter operationsHistoryPrinter;
    @Test
    public void should_build_operation_List_and_call_console_to_print(){
        List<Transaction> transactions = Arrays.asList(
        Transaction.builder().type(DEPOSIT).amount(100).date(LocalDate.of(2020,10,7)).build(),
        Transaction.builder().type(DEPOSIT).amount(60).date(LocalDate.of(2020,10,8)).build(),
        Transaction.builder().type(WITHDRAWAL).amount(60).date(LocalDate.of(2020,10,8)).build());

        operationsHistoryPrinter.printHistory(transactions);

        List<Operation> expectedOperations =  Arrays.asList(
                Operation.builder().operation(DEPOSIT.name()).amount("100").date(LocalDate.of(2020,10,7).toString()).balance("100").build(),
                Operation.builder().operation(DEPOSIT.name()).amount("60").date(LocalDate.of(2020,10,8).toString()).balance("160").build(),
                Operation.builder().operation(WITHDRAWAL.name()).amount("60").date(LocalDate.of(2020,10,8).toString()).balance("100").build());

        verify(console).print(expectedOperations);
    }
}