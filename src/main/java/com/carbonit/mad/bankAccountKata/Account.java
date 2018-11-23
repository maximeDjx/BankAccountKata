package com.carbonit.mad.bankAccountKata;

public class Account {
    private final OperationsRepository operationsRepository;
    private final DateTimeService dateTimeService;

    private Amount balance;

    private Account(Amount balance, OperationsRepository operationsRepository, DateTimeService dateTimeService) {
        this.balance = balance;
        this.operationsRepository = operationsRepository;
        this.dateTimeService = dateTimeService;
    }

    public static Account create(OperationsRepository operationsRepository, DateTimeService dateTimeService) {
        final var balance = computeBalance(operationsRepository);

        return new Account(balance, operationsRepository, dateTimeService);
    }

    public void deposit(Amount amount) throws IllegalArgumentException {
        final var newBalance = balance.add(amount);
        final var operation = Operation.createOperation(OperationType.DEPOSIT, this.dateTimeService.getDateTime(), amount, newBalance);

        operationsRepository.add(operation);
    }

    public void withdraw(Amount amount) {
        final var balance = computeBalance(this.operationsRepository);
        final var newBalance = balance.subtract(amount);
        final var operation = Operation.createOperation(OperationType.WITHDRAWAL, this.dateTimeService.getDateTime(), amount, newBalance);

        operationsRepository.add(operation);
    }

    private static Amount computeBalance(OperationsRepository operationsRepository) {
        final var optionalOperation = operationsRepository.getLast();

        if (optionalOperation.isPresent()) {
            return optionalOperation.get().getBalance();
        } else {
            return Amount.ZERO;
        }
    }

    public void printStatement(final OperationsFormatter operationsFormatter, final OutputWriter writer) {
        writer.write(operationsFormatter.formatOperations(this.operationsRepository.getAll()));
    }
}
