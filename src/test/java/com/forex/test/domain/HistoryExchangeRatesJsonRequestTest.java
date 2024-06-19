package com.forex.test.domain;

import static com.forex.test.domain.HistoryExchangeRatesJsonRequestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.forex.test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistoryExchangeRatesJsonRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoryExchangeRatesJsonRequest.class);
        HistoryExchangeRatesJsonRequest historyExchangeRatesJsonRequest1 = getHistoryExchangeRatesJsonRequestSample1();
        HistoryExchangeRatesJsonRequest historyExchangeRatesJsonRequest2 = new HistoryExchangeRatesJsonRequest();
        assertThat(historyExchangeRatesJsonRequest1).isNotEqualTo(historyExchangeRatesJsonRequest2);

        historyExchangeRatesJsonRequest2.setId(historyExchangeRatesJsonRequest1.getId());
        assertThat(historyExchangeRatesJsonRequest1).isEqualTo(historyExchangeRatesJsonRequest2);

        historyExchangeRatesJsonRequest2 = getHistoryExchangeRatesJsonRequestSample2();
        assertThat(historyExchangeRatesJsonRequest1).isNotEqualTo(historyExchangeRatesJsonRequest2);
    }
}
