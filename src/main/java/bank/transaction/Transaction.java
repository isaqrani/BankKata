package bank.transaction;

public class Transaction {

    private TransactionType type;
    private int amount;

    public Transaction(TransactionType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }
}
