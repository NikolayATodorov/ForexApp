package com.forex.test.service.mapper;

import com.forex.test.domain.CurrentExchangeRatesJsonRequest;
import com.forex.test.service.dto.CurrentExchangeRatesJsonRequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link CurrentExchangeRatesJsonRequest} and its DTO {@link CurrentExchangeRatesJsonRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface CurrentExchangeRatesJsonRequestMapper
    extends EntityMapper<CurrentExchangeRatesJsonRequestDTO, CurrentExchangeRatesJsonRequest> {}
