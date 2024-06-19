package com.forex.test.service.mapper;

import com.forex.test.domain.ExchangeRate;
import com.forex.test.service.dto.ExchangeRateDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link ExchangeRate} and its DTO {@link ExchangeRateDTO}.
 */
@Mapper(componentModel = "spring")
public interface ExchangeRateMapper extends EntityMapper<ExchangeRateDTO, ExchangeRate> {}
