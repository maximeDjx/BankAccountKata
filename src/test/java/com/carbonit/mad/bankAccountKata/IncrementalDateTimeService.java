package com.carbonit.mad.bankAccountKata;

import java.time.Instant;

public class IncrementalDateTimeService implements DateTimeService {
    private static final long ONE_DAY_IN_SECONDS = 86400;
    private long current;

    public IncrementalDateTimeService(Instant beginning) {
        current = beginning.getEpochSecond();
    }

    @Override
    public Instant getDateTime() {
        Instant result = Instant.ofEpochSecond(current);
        current += ONE_DAY_IN_SECONDS;
        return result;
    }
}
