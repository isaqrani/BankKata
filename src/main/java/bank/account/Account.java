package bank.account;

import bank.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private Client client;
    private List<Transaction> transactions;

    public Account(Client client) {
        this.transactions = new ArrayList<>();
        this.client = client;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        /* Return a copy of transaction's list */
        return new ArrayList(this.transactions);
    }
}
