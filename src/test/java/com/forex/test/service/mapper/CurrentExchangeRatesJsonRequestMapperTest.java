package com.forex.test.service.mapper;

import static com.forex.test.domain.CurrentExchangeRatesJsonRequestAsserts.*;
import static com.forex.test.domain.CurrentExchangeRatesJsonRequestTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CurrentExchangeRatesJsonRequestMapperTest {

    private CurrentExchangeRatesJsonRequestMapper currentExchangeRatesJsonRequestMapper;

    @BeforeEach
    void setUp() {
        currentExchangeRatesJsonRequestMapper = new CurrentExchangeRatesJsonRequestMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getCurrentExchangeRatesJsonRequestSample1();
        var actual = currentExchangeRatesJsonRequestMapper.toEntity(currentExchangeRatesJsonRequestMapper.toDto(expected));
        assertCurrentExchangeRatesJsonRequestAllPropertiesEquals(expected, actual);
    }
}
