package com.forex.test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.forex.test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ExchangeRateDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExchangeRateDTO.class);
        ExchangeRateDTO exchangeRateDTO1 = new ExchangeRateDTO();
        exchangeRateDTO1.setId(1L);
        ExchangeRateDTO exchangeRateDTO2 = new ExchangeRateDTO();
        assertThat(exchangeRateDTO1).isNotEqualTo(exchangeRateDTO2);
        exchangeRateDTO2.setId(exchangeRateDTO1.getId());
        assertThat(exchangeRateDTO1).isEqualTo(exchangeRateDTO2);
        exchangeRateDTO2.setId(2L);
        assertThat(exchangeRateDTO1).isNotEqualTo(exchangeRateDTO2);
        exchangeRateDTO1.setId(null);
        assertThat(exchangeRateDTO1).isNotEqualTo(exchangeRateDTO2);
    }
}
