package bank.transaction;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Transaction {

    private final TransactionType type;
    private final int amount;
    private final LocalDate date;

}
