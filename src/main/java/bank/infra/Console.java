package bank.infra;

import bank.history.Operation;

import java.util.List;

public interface Console {
    void print(List<Operation> operations);
}
