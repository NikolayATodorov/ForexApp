package com.forex.test.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HistoryExchangeRatesJsonRequestTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static HistoryExchangeRatesJsonRequest getHistoryExchangeRatesJsonRequestSample1() {
        return new HistoryExchangeRatesJsonRequest().id(1L).requestId("requestId1").client("client1").currency("currency1").period(1);
    }

    public static HistoryExchangeRatesJsonRequest getHistoryExchangeRatesJsonRequestSample2() {
        return new HistoryExchangeRatesJsonRequest().id(2L).requestId("requestId2").client("client2").currency("currency2").period(2);
    }

    public static HistoryExchangeRatesJsonRequest getHistoryExchangeRatesJsonRequestRandomSampleGenerator() {
        return new HistoryExchangeRatesJsonRequest()
            .id(longCount.incrementAndGet())
            .requestId(UUID.randomUUID().toString())
            .client(UUID.randomUUID().toString())
            .currency(UUID.randomUUID().toString())
            .period(intCount.incrementAndGet());
    }
}
