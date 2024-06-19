package com.forex.test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.forex.test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HistoryExchangeRatesJsonRequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoryExchangeRatesJsonRequestDTO.class);
        HistoryExchangeRatesJsonRequestDTO historyExchangeRatesJsonRequestDTO1 = new HistoryExchangeRatesJsonRequestDTO();
        historyExchangeRatesJsonRequestDTO1.setId(1L);
        HistoryExchangeRatesJsonRequestDTO historyExchangeRatesJsonRequestDTO2 = new HistoryExchangeRatesJsonRequestDTO();
        assertThat(historyExchangeRatesJsonRequestDTO1).isNotEqualTo(historyExchangeRatesJsonRequestDTO2);
        historyExchangeRatesJsonRequestDTO2.setId(historyExchangeRatesJsonRequestDTO1.getId());
        assertThat(historyExchangeRatesJsonRequestDTO1).isEqualTo(historyExchangeRatesJsonRequestDTO2);
        historyExchangeRatesJsonRequestDTO2.setId(2L);
        assertThat(historyExchangeRatesJsonRequestDTO1).isNotEqualTo(historyExchangeRatesJsonRequestDTO2);
        historyExchangeRatesJsonRequestDTO1.setId(null);
        assertThat(historyExchangeRatesJsonRequestDTO1).isNotEqualTo(historyExchangeRatesJsonRequestDTO2);
    }
}
