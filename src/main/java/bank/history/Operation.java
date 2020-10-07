package bank.history;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
@Builder
public class Operation {
    private final String operation;
    private final String date;
    private final String amount;
    private final String balance;
}
