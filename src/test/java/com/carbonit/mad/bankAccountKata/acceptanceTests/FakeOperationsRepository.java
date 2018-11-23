package com.carbonit.mad.bankAccountKata.acceptanceTests;

import com.carbonit.mad.bankAccountKata.Operation;
import com.carbonit.mad.bankAccountKata.OperationsRepository;

import java.util.*;

public class FakeOperationsRepository implements OperationsRepository {

    private static final List<Operation> operations = new LinkedList<>();

    @Override
    public List<Operation> getAll() {
        return Collections.unmodifiableList(operations);
    }

    @Override
    public Optional<Operation> getLast() {
        if(operations.size() > 0)
            return Optional.of(operations.get(operations.size() - 1));
        else
            return Optional.empty();
    }

    @Override
    public void add(Operation operation) {
        operations.add(operation);
    }
}
