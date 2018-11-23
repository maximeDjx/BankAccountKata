package com.carbonit.mad.bankAccountKata.acceptanceTests;

import com.carbonit.mad.bankAccountKata.Account;
import com.carbonit.mad.bankAccountKata.Amount;
import com.carbonit.mad.bankAccountKata.IncrementalDateTimeService;
import com.carbonit.mad.bankAccountKata.dependencies.BasicOperationsFormatter;
import com.carbonit.mad.bankAccountKata.dependencies.ConsoleOutputWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.Instant;

@ExtendWith(MockitoExtension.class)
public class BankAccountAcceptanceTests {
    private ByteArrayOutputStream ConsoleOutputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUpStreams() {
        ConsoleOutputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(ConsoleOutputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void userStoryTest() {
        final var basicFormatter = new BasicOperationsFormatter();
        final var consoleOutputWriter = new ConsoleOutputWriter();
        final var operationRepository = new FakeOperationsRepository();
        final var incrementalDateTimeService = new IncrementalDateTimeService(Instant.parse("1992-12-25T00:00:00Z"));

        final var expected =    "Type operation=DEPOSIT; date=1992-12-25; amount=200; balance=200\n" +
                                "Type operation=WITHDRAWAL; date=1992-12-26; amount=50; balance=150";

        final var depositAmount = Amount.of(BigDecimal.valueOf(200));
        final var withdrawalAmount = Amount.of(BigDecimal.valueOf(50));

        final var account = Account.create(operationRepository, incrementalDateTimeService);

        account.deposit(depositAmount);
        account.withdraw(withdrawalAmount);
        account.printStatement(basicFormatter, consoleOutputWriter);

        final var actual = new String(ConsoleOutputStream.toByteArray());

        Assertions.assertEquals(expected, actual);
    }
}
