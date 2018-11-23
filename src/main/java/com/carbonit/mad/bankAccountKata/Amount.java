package com.carbonit.mad.bankAccountKata;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {

    public final static Amount ZERO = new Amount(BigDecimal.ZERO);
    private static final String ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "Un Amount doit avoir une valeur positive.";
    private final BigDecimal value;

    private Amount(BigDecimal value) {
        this.value = value;
    }

    public static Amount of(BigDecimal value) {
        if (value.signum() == -1) {
            throw new IllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE);
        }

        return new Amount(value);
    }

    public Amount add(Amount amount) {
        return new Amount(this.value.add(amount.value));
    }

    public Amount subtract(Amount amount) {
        return Amount.of(this.value.subtract(amount.value));
    }

    public BigDecimal asBigDecimal() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Amount)) return false;
        if (obj == this) return true;
        final var other = (Amount) obj;

        return this.value.equals(other.value);
    }


    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
