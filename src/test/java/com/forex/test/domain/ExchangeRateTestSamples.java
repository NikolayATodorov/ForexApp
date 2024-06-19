package com.forex.test.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ExchangeRateTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ExchangeRate getExchangeRateSample1() {
        return new ExchangeRate().id(1L).base("base1");
    }

    public static ExchangeRate getExchangeRateSample2() {
        return new ExchangeRate().id(2L).base("base2");
    }

    public static ExchangeRate getExchangeRateRandomSampleGenerator() {
        return new ExchangeRate().id(longCount.incrementAndGet()).base(UUID.randomUUID().toString());
    }
}
