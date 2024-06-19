package com.forex.test.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.forex.test.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CurrentExchangeRatesJsonRequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CurrentExchangeRatesJsonRequestDTO.class);
        CurrentExchangeRatesJsonRequestDTO currentExchangeRatesJsonRequestDTO1 = new CurrentExchangeRatesJsonRequestDTO();
        currentExchangeRatesJsonRequestDTO1.setId(1L);
        CurrentExchangeRatesJsonRequestDTO currentExchangeRatesJsonRequestDTO2 = new CurrentExchangeRatesJsonRequestDTO();
        assertThat(currentExchangeRatesJsonRequestDTO1).isNotEqualTo(currentExchangeRatesJsonRequestDTO2);
        currentExchangeRatesJsonRequestDTO2.setId(currentExchangeRatesJsonRequestDTO1.getId());
        assertThat(currentExchangeRatesJsonRequestDTO1).isEqualTo(currentExchangeRatesJsonRequestDTO2);
        currentExchangeRatesJsonRequestDTO2.setId(2L);
        assertThat(currentExchangeRatesJsonRequestDTO1).isNotEqualTo(currentExchangeRatesJsonRequestDTO2);
        currentExchangeRatesJsonRequestDTO1.setId(null);
        assertThat(currentExchangeRatesJsonRequestDTO1).isNotEqualTo(currentExchangeRatesJsonRequestDTO2);
    }
}
