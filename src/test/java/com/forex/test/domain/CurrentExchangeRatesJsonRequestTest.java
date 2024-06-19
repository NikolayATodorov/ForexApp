package com.forex.test.domain;

import static com.forex.test.domain.CurrentExchangeRatesJsonRequestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.forex.test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CurrentExchangeRatesJsonRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrentExchangeRatesJsonRequest.class);
        CurrentExchangeRatesJsonRequest currentExchangeRatesJsonRequest1 = getCurrentExchangeRatesJsonRequestSample1();
        CurrentExchangeRatesJsonRequest currentExchangeRatesJsonRequest2 = new CurrentExchangeRatesJsonRequest();
        assertThat(currentExchangeRatesJsonRequest1).isNotEqualTo(currentExchangeRatesJsonRequest2);

        currentExchangeRatesJsonRequest2.setId(currentExchangeRatesJsonRequest1.getId());
        assertThat(currentExchangeRatesJsonRequest1).isEqualTo(currentExchangeRatesJsonRequest2);

        currentExchangeRatesJsonRequest2 = getCurrentExchangeRatesJsonRequestSample2();
        assertThat(currentExchangeRatesJsonRequest1).isNotEqualTo(currentExchangeRatesJsonRequest2);
    }
}
