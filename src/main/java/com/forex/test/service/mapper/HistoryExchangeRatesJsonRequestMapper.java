package com.forex.test.service.mapper;

import com.forex.test.domain.HistoryExchangeRatesJsonRequest;
import com.forex.test.service.dto.HistoryExchangeRatesJsonRequestDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link HistoryExchangeRatesJsonRequest} and its DTO {@link HistoryExchangeRatesJsonRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface HistoryExchangeRatesJsonRequestMapper
    extends EntityMapper<HistoryExchangeRatesJsonRequestDTO, HistoryExchangeRatesJsonRequest> {}
