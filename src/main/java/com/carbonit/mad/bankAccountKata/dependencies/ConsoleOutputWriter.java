package com.carbonit.mad.bankAccountKata.dependencies;

import com.carbonit.mad.bankAccountKata.OutputWriter;

public class ConsoleOutputWriter implements OutputWriter {
    @Override
    public void write(String input) {
        System.out.print(input);
    }
}
