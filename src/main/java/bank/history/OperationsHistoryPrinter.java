package bank.history;

import bank.infra.Console;
import bank.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import static bank.transaction.TransactionType.WITHDRAWAL;

public class OperationsHistoryPrinter {
    private final Console console;

    public OperationsHistoryPrinter(Console console) {
        this.console = console;
    }

    public void printHistory(List<Transaction> transactions){
    List<Operation> operations = new ArrayList<>();
    int currentBalance = 0;
        for (Transaction t : transactions) {
            currentBalance = t.getType().equals(WITHDRAWAL) ? currentBalance-t.getAmount() : currentBalance+t.getAmount();
            operations.add(Operation.builder()
                    .operation(t.getType().name())
                    .amount(String.valueOf(t.getAmount()))
                    .date(t.getDate().toString())
                    .balance(String.valueOf(currentBalance)).build());
        }
        console.print(operations);
    }
}
