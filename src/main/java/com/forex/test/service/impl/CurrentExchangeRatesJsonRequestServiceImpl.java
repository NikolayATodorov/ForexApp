package com.forex.test.service.impl;

import com.forex.test.domain.CurrentExchangeRatesJsonRequest;
import com.forex.test.repository.CurrentExchangeRatesJsonRequestRepository;
import com.forex.test.service.CurrentExchangeRatesJsonRequestService;
import com.forex.test.service.dto.CurrentExchangeRatesJsonRequestDTO;
import com.forex.test.service.mapper.CurrentExchangeRatesJsonRequestMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CurrentExchangeRatesJsonRequest}.
 */
@Service
@Transactional
public class CurrentExchangeRatesJsonRequestServiceImpl implements CurrentExchangeRatesJsonRequestService {

    private final Logger log = LoggerFactory.getLogger(CurrentExchangeRatesJsonRequestServiceImpl.class);

    private final CurrentExchangeRatesJsonRequestRepository currentExchangeRatesJsonRequestRepository;

    private final CurrentExchangeRatesJsonRequestMapper currentExchangeRatesJsonRequestMapper;

    public CurrentExchangeRatesJsonRequestServiceImpl(
        CurrentExchangeRatesJsonRequestRepository currentExchangeRatesJsonRequestRepository,
        CurrentExchangeRatesJsonRequestMapper currentExchangeRatesJsonRequestMapper
    ) {
        this.currentExchangeRatesJsonRequestRepository = currentExchangeRatesJsonRequestRepository;
        this.currentExchangeRatesJsonRequestMapper = currentExchangeRatesJsonRequestMapper;
    }

    @Override
    public CurrentExchangeRatesJsonRequestDTO save(CurrentExchangeRatesJsonRequestDTO currentExchangeRatesJsonRequestDTO) {
        log.debug("Request to save CurrentExchangeRatesJsonRequest : {}", currentExchangeRatesJsonRequestDTO);
        CurrentExchangeRatesJsonRequest currentExchangeRatesJsonRequest = currentExchangeRatesJsonRequestMapper.toEntity(
            currentExchangeRatesJsonRequestDTO
        );
        currentExchangeRatesJsonRequest = currentExchangeRatesJsonRequestRepository.save(currentExchangeRatesJsonRequest);
        return currentExchangeRatesJsonRequestMapper.toDto(currentExchangeRatesJsonRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CurrentExchangeRatesJsonRequestDTO> findAll() {
        log.debug("Request to get all CurrentExchangeRatesJsonRequests");
        return currentExchangeRatesJsonRequestRepository
            .findAll()
            .stream()
            .map(currentExchangeRatesJsonRequestMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrentExchangeRatesJsonRequestDTO> findOne(Long id) {
        log.debug("Request to get CurrentExchangeRatesJsonRequest : {}", id);
        return currentExchangeRatesJsonRequestRepository.findById(id).map(currentExchangeRatesJsonRequestMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CurrentExchangeRatesJsonRequestDTO> findByRequestId(String requestId) {
        log.debug("Request to get CurrentExchangeRatesJsonRequest : {}", requestId);
        return currentExchangeRatesJsonRequestRepository.findByRequestId(requestId).map(currentExchangeRatesJsonRequestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CurrentExchangeRatesJsonRequest : {}", id);
        currentExchangeRatesJsonRequestRepository.deleteById(id);
    }
}
