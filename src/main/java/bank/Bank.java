package bank;

import bank.account.Account;
import bank.account.Client;
import bank.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bank.transaction.TransactionType.DEPOSIT;
import static bank.transaction.TransactionType.WITHDRAWAL;

public class Bank {

    private final List<Account> accounts;
    private final Clock clock;
    private final OperationsHistoryPrinter operationsHistoryPrinter;

    public Bank(Clock clock, OperationsHistoryPrinter operationsHistoryPrinter) {
        this.accounts = new ArrayList<>();
        this.clock = clock;
        this.operationsHistoryPrinter = operationsHistoryPrinter;
    }

    public Account createAccount(Client client, int balance) {
        Account account = new Account(client);
        account.addTransaction(Transaction.builder()
                .amount(balance)
                .type(DEPOSIT)
                .date(clock.now())
                .build());
        accounts.add(account);
        return account;
    }

    public void withdraw(Account account, int amount) {

        account.addTransaction(Transaction.builder()
                .amount(amount)
                .type(WITHDRAWAL)
                .date(clock.now())
                .build());
    }
    
    public void deposit(Account account, int amount) {
    	account.addTransaction(Transaction.builder()
                .amount(amount)
                .type(DEPOSIT)
                .date(clock.now())
                .build());
	}

    public int calculateBalance(Account account) {
        int totalDeposit;
        int totalWithdrawal;
        List<Transaction> transactions = account.getTransactions();
        totalDeposit = transactions.stream().filter(t -> t.getType().equals(DEPOSIT))
                .mapToInt(Transaction::getAmount)
                .sum();
        totalWithdrawal = transactions.stream().filter(t -> t.getType().equals(WITHDRAWAL))
                .mapToInt(Transaction::getAmount)
                .sum();

        return totalDeposit - totalWithdrawal;
    }

    public List<Account> getAccounts() {
        return this.accounts.stream().map(Account::copy).collect(Collectors.toList());
    }

    public void printHistory(Account account) {
        operationsHistoryPrinter.printHistory(account.getTransactions());
    }
}
