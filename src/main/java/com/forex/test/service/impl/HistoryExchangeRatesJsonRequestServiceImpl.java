package com.forex.test.service.impl;

import com.forex.test.domain.HistoryExchangeRatesJsonRequest;
import com.forex.test.repository.HistoryExchangeRatesJsonRequestRepository;
import com.forex.test.service.HistoryExchangeRatesJsonRequestService;
import com.forex.test.service.dto.HistoryExchangeRatesJsonRequestDTO;
import com.forex.test.service.mapper.HistoryExchangeRatesJsonRequestMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link HistoryExchangeRatesJsonRequest}.
 */
@Service
@Transactional
public class HistoryExchangeRatesJsonRequestServiceImpl implements HistoryExchangeRatesJsonRequestService {

    private final Logger log = LoggerFactory.getLogger(HistoryExchangeRatesJsonRequestServiceImpl.class);

    private final HistoryExchangeRatesJsonRequestRepository historyExchangeRatesJsonRequestRepository;

    private final HistoryExchangeRatesJsonRequestMapper historyExchangeRatesJsonRequestMapper;

    public HistoryExchangeRatesJsonRequestServiceImpl(
        HistoryExchangeRatesJsonRequestRepository historyExchangeRatesJsonRequestRepository,
        HistoryExchangeRatesJsonRequestMapper historyExchangeRatesJsonRequestMapper
    ) {
        this.historyExchangeRatesJsonRequestRepository = historyExchangeRatesJsonRequestRepository;
        this.historyExchangeRatesJsonRequestMapper = historyExchangeRatesJsonRequestMapper;
    }

    @Override
    public HistoryExchangeRatesJsonRequestDTO save(HistoryExchangeRatesJsonRequestDTO historyExchangeRatesJsonRequestDTO) {
        log.debug("Request to save HistoryExchangeRatesJsonRequest : {}", historyExchangeRatesJsonRequestDTO);
        HistoryExchangeRatesJsonRequest historyExchangeRatesJsonRequest = historyExchangeRatesJsonRequestMapper.toEntity(
            historyExchangeRatesJsonRequestDTO
        );
        historyExchangeRatesJsonRequest = historyExchangeRatesJsonRequestRepository.save(historyExchangeRatesJsonRequest);
        return historyExchangeRatesJsonRequestMapper.toDto(historyExchangeRatesJsonRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<HistoryExchangeRatesJsonRequestDTO> findAll() {
        log.debug("Request to get all HistoryExchangeRatesJsonRequests");
        return historyExchangeRatesJsonRequestRepository
            .findAll()
            .stream()
            .map(historyExchangeRatesJsonRequestMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistoryExchangeRatesJsonRequestDTO> findOne(Long id) {
        log.debug("Request to get HistoryExchangeRatesJsonRequest : {}", id);
        return historyExchangeRatesJsonRequestRepository.findById(id).map(historyExchangeRatesJsonRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HistoryExchangeRatesJsonRequestDTO> findByRequestId(String requestId) {
        return historyExchangeRatesJsonRequestRepository.findByRequestId(requestId).map(historyExchangeRatesJsonRequestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistoryExchangeRatesJsonRequest : {}", id);
        historyExchangeRatesJsonRequestRepository.deleteById(id);
    }
}
