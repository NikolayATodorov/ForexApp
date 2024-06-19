package com.forex.test.service.mapper;

import static com.forex.test.domain.ExchangeRateAsserts.*;
import static com.forex.test.domain.ExchangeRateTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExchangeRateMapperTest {

    private ExchangeRateMapper exchangeRateMapper;

    @BeforeEach
    void setUp() {
        exchangeRateMapper = new ExchangeRateMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getExchangeRateSample1();
        var actual = exchangeRateMapper.toEntity(exchangeRateMapper.toDto(expected));
        assertExchangeRateAllPropertiesEquals(expected, actual);
    }
}
