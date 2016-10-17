package bank;

import bank.account.Account;
import bank.account.Client;
import bank.transaction.Transaction;
import bank.transaction.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public Account createAccount(Client client, int balance) {
        Account account = new Account(client);
        account.addTransaction(new Transaction(TransactionType.DEPOSIT, balance));
        accounts.add(account);
        return account;
    }

    public void withdraw(Account account, int amount) {
        account.addTransaction(new Transaction(TransactionType.WITHDRAWAL, amount));
    }

    public int calculateBalance(Account account) {
        int totalDeposit, totalWithdraw;
        List<Transaction> transactions = account.getTransactions();
        totalDeposit = transactions.stream().filter(t -> t.getType().equals(TransactionType.DEPOSIT))
                .mapToInt(t -> t.getAmount())
                .sum();
        totalWithdraw = transactions.stream().filter(t -> t.getType().equals(TransactionType.WITHDRAWAL))
                .mapToInt(t -> t.getAmount())
                .sum();
        return totalDeposit - totalWithdraw;
    }

    public List<Account> getAccounts() {
        return new ArrayList<>(this.accounts);
    }
}
