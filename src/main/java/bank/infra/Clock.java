package bank.infra;

import java.time.LocalDate;

public interface Clock {
    LocalDate now();
}
