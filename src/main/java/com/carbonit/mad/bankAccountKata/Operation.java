package com.carbonit.mad.bankAccountKata;

import java.time.Instant;
import java.util.Objects;

public class Operation {
    private final OperationType operationType;
    private final Instant date;
    private final Amount amount;
    private final Amount balance;

    private Operation(OperationType operationType, Instant date, Amount amount, Amount balance) {
        this.operationType = operationType;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public static Operation createOperation(OperationType operationType, Instant date, Amount amount, Amount balance) {
        return new Operation(operationType, date, amount, balance);
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public Instant getDate() {
        return date;
    }

    public Amount getAmount() {
        return amount;
    }

    public Amount getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Operation)) return false;
        if (other == this) return true;

        var otherOperation = (Operation) other;

        return (this.getOperationType() == otherOperation.getOperationType()) &&
                this.getBalance().equals(otherOperation.getBalance()) &&
                this.getAmount().equals(otherOperation.getAmount()) &&
                this.getDate().equals(otherOperation.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationType, date, amount, balance);
    }
}
