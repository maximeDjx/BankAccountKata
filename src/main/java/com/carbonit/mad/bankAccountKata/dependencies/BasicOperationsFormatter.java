package com.carbonit.mad.bankAccountKata.dependencies;

import com.carbonit.mad.bankAccountKata.OperationsFormatter;
import com.carbonit.mad.bankAccountKata.Operation;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BasicOperationsFormatter implements OperationsFormatter {

    @Override
    public String formatOperations(List<Operation> operations) {
        return operations.stream().map(BasicOperationsFormatter::stringOperationFormatter).collect(Collectors.joining("\n"));
    }

    private static String stringOperationFormatter(Operation operation) {
        return "Type operation=" + operation.getOperationType() +
                "; date=" + operation.getDate().atZone(ZoneId.of("UTC")).format(DateTimeFormatter.ISO_LOCAL_DATE) +
                "; amount=" + operation.getAmount() +
                "; balance=" + operation.getBalance();
    }
}
