package com.carbonit.mad.bankAccountKata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Instant;

public class OperationTests {

    private static final Instant DATE = Instant.parse("1992-12-25T00:00:00Z");
    @Test
    @DisplayName("verify if deposit operation contain the right arguments after creations")
    public void check_deposit_operation_creation() {
        final Amount amount = Amount.of(BigDecimal.valueOf(10));
        final Amount balance = Amount.of(BigDecimal.valueOf(50));

        Operation depositOperation = Operation.createOperation(OperationType.DEPOSIT, DATE, amount, balance);
        //Assert
        Assertions.assertEquals(depositOperation.getOperationType(), OperationType.DEPOSIT);
        Assertions.assertEquals(depositOperation.getDate(),DATE);
        Assertions.assertEquals(depositOperation.getAmount(),Amount.of(BigDecimal.valueOf(10)));
        Assertions.assertEquals(depositOperation.getBalance(),Amount.of(BigDecimal.valueOf(50)));
    }
    @Test
    @DisplayName("verify if withdrawal operation contain the right arguments after creation")
    public void check_withdrawal_operation_creation() {
        final Amount amount = Amount.of(BigDecimal.valueOf(10));
        final Amount balance = Amount.of(BigDecimal.valueOf(40));

        Operation withdrawalOperation = Operation.createOperation(OperationType.WITHDRAWAL, DATE, amount, balance);
        //Assert
        Assertions.assertEquals(withdrawalOperation.getOperationType(), OperationType.WITHDRAWAL);
        Assertions.assertEquals(withdrawalOperation.getDate(),DATE);
        Assertions.assertEquals(withdrawalOperation.getAmount(),Amount.of(BigDecimal.valueOf(10)));
        Assertions.assertEquals(withdrawalOperation.getBalance(),Amount.of(BigDecimal.valueOf(40)));
    }
}
