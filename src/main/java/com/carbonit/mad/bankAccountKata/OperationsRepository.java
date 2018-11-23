package com.carbonit.mad.bankAccountKata;

import java.util.List;
import java.util.Optional;

public interface OperationsRepository {
    List<Operation> getAll();
    Optional<Operation> getLast();
    void add(Operation operation);
}
