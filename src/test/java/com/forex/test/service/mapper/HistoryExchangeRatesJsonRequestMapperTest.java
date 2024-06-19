package com.forex.test.service.mapper;

import static com.forex.test.domain.HistoryExchangeRatesJsonRequestAsserts.*;
import static com.forex.test.domain.HistoryExchangeRatesJsonRequestTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HistoryExchangeRatesJsonRequestMapperTest {

    private HistoryExchangeRatesJsonRequestMapper historyExchangeRatesJsonRequestMapper;

    @BeforeEach
    void setUp() {
        historyExchangeRatesJsonRequestMapper = new HistoryExchangeRatesJsonRequestMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getHistoryExchangeRatesJsonRequestSample1();
        var actual = historyExchangeRatesJsonRequestMapper.toEntity(historyExchangeRatesJsonRequestMapper.toDto(expected));
        assertHistoryExchangeRatesJsonRequestAllPropertiesEquals(expected, actual);
    }
}
