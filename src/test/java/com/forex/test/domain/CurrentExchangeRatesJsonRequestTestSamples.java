package com.forex.test.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CurrentExchangeRatesJsonRequestTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CurrentExchangeRatesJsonRequest getCurrentExchangeRatesJsonRequestSample1() {
        return new CurrentExchangeRatesJsonRequest().id(1L).requestId("requestId1").client("client1").currency("currency1");
    }

    public static CurrentExchangeRatesJsonRequest getCurrentExchangeRatesJsonRequestSample2() {
        return new CurrentExchangeRatesJsonRequest().id(2L).requestId("requestId2").client("client2").currency("currency2");
    }

    public static CurrentExchangeRatesJsonRequest getCurrentExchangeRatesJsonRequestRandomSampleGenerator() {
        return new CurrentExchangeRatesJsonRequest()
            .id(longCount.incrementAndGet())
            .requestId(UUID.randomUUID().toString())
            .client(UUID.randomUUID().toString())
            .currency(UUID.randomUUID().toString());
    }
}
