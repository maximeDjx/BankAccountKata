package com.carbonit.mad.bankAccountKata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class AmountTests {

    @Test
    public void given_two_numbers_should_return_the_addition_of_these_numbers() {
        final var a = Amount.of(BigDecimal.valueOf(10));
        final var b = Amount.of(BigDecimal.valueOf(10));

        final var actual = a.add(b);

        Assertions.assertEquals(BigDecimal.valueOf(20), actual.asBigDecimal());
    }

    @Test
    public void given_two_numbers_should_return_the_subtraction_of_these_numbers() {
        final var a = Amount.of(BigDecimal.valueOf(20));
        final var b = Amount.of(BigDecimal.valueOf(10));

        final var actual = a.subtract(b);

        Assertions.assertEquals(BigDecimal.valueOf(10), actual.asBigDecimal());
    }

    @Test
    public void create_negative_amount_should_throw_exception() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Amount.of(BigDecimal.valueOf(-10)));
    }
}
