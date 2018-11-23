package com.carbonit.mad.bankAccountKata;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AccountTests {
    private static final BigDecimal DEPOSIT_DEFAULT_AMOUNT = BigDecimal.valueOf(20);
    private static final BigDecimal WITHDRAWAL_DEFAULT_AMOUNT = BigDecimal.valueOf(10);
    private static final Instant DATE = Instant.parse("1992-12-25T00:00:00Z");
    private static final String SAMPLE_TEXT = "sample text";

    @Mock
    private OperationsRepository operationsRepositoryMock;
    @Mock
    private DateTimeService dateTimeServiceStub;
    @Mock
    private OutputWriter outputWriterMock;
    @Mock
    private OperationsFormatter operationsFormatterMock;

    @Test
    public void should_be_able_to_deposit_money() {
        when(dateTimeServiceStub.getDateTime()).thenReturn(DATE);

        final var account = Account.create(operationsRepositoryMock, dateTimeServiceStub);
        account.deposit(Amount.of(DEPOSIT_DEFAULT_AMOUNT));

        final var expectedOperation = Operation.createOperation(OperationType.DEPOSIT, DATE, Amount.of(DEPOSIT_DEFAULT_AMOUNT), Amount.of(DEPOSIT_DEFAULT_AMOUNT));

        verify(operationsRepositoryMock).add(expectedOperation);
    }

    @Test
    public void should_be_able_to_withdraw_money() {
        when(dateTimeServiceStub.getDateTime()).thenReturn(DATE);

        final Operation operation = Operation.createOperation(OperationType.DEPOSIT, DATE, Amount.of(DEPOSIT_DEFAULT_AMOUNT), Amount.of(DEPOSIT_DEFAULT_AMOUNT));
        when(operationsRepositoryMock.getLast()).thenReturn(Optional.of(operation));

        final var account = Account.create(operationsRepositoryMock, dateTimeServiceStub);

        account.withdraw(Amount.of(WITHDRAWAL_DEFAULT_AMOUNT));

        final Amount expectedBalance = Amount.of(DEPOSIT_DEFAULT_AMOUNT.subtract(WITHDRAWAL_DEFAULT_AMOUNT));
        final Operation expectedOperation = Operation.createOperation(OperationType.WITHDRAWAL, DATE, Amount.of(WITHDRAWAL_DEFAULT_AMOUNT), expectedBalance);

        verify(operationsRepositoryMock).add(expectedOperation);
    }

    @Test
    public void should_throw_exception_when_account_does_not_have_enough_money() {
        when(operationsRepositoryMock.getLast()).thenReturn(Optional.empty());

        final var account = Account.create(operationsRepositoryMock, dateTimeServiceStub);

        Assertions.assertThrows(IllegalArgumentException.class, () -> account.withdraw(Amount.of(WITHDRAWAL_DEFAULT_AMOUNT)));
        verify(operationsRepositoryMock, times(2)).getLast();
        verifyNoMoreInteractions(operationsRepositoryMock);
    }

    @Test
    public void verify_result_printStatement() {
        final var operations = new ArrayList<Operation>();
        operations.add(Operation.createOperation(OperationType.DEPOSIT, DATE, Amount.of(WITHDRAWAL_DEFAULT_AMOUNT), Amount.of(BigDecimal.valueOf(30))));
        when(operationsRepositoryMock.getAll()).thenReturn(operations);
        when(operationsFormatterMock.formatOperations(operations)).thenReturn(SAMPLE_TEXT);

        final var account = Account.create(operationsRepositoryMock, dateTimeServiceStub);
        account.printStatement(operationsFormatterMock, outputWriterMock);

        verify(operationsRepositoryMock).getAll();

        verify(operationsFormatterMock).formatOperations(operations);
        verifyNoMoreInteractions(operationsFormatterMock);

        verify(outputWriterMock).write(SAMPLE_TEXT);
        verifyNoMoreInteractions(outputWriterMock);
    }
}