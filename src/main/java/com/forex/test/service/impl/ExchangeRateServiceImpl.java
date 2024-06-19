package com.forex.test.service.impl;

import com.forex.test.domain.ExchangeRate;
import com.forex.test.repository.ExchangeRateRepository;
import com.forex.test.service.ExchangeRateService;
import com.forex.test.service.dto.ExchangeRateDTO;
import com.forex.test.service.mapper.ExchangeRateMapper;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ExchangeRate}.
 */
@Service
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final Logger log = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    private final ExchangeRateRepository exchangeRateRepository;

    private final ExchangeRateMapper exchangeRateMapper;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, ExchangeRateMapper exchangeRateMapper) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateMapper = exchangeRateMapper;
    }

    @Override
    public ExchangeRateDTO save(ExchangeRateDTO exchangeRateDTO) {
        log.debug("Request to save ExchangeRate : {}", exchangeRateDTO);
        ExchangeRate exchangeRate = exchangeRateMapper.toEntity(exchangeRateDTO);
        exchangeRate = exchangeRateRepository.save(exchangeRate);
        return exchangeRateMapper.toDto(exchangeRate);
    }

    @Override
    public ExchangeRateDTO update(ExchangeRateDTO exchangeRateDTO) {
        log.debug("Request to update ExchangeRate : {}", exchangeRateDTO);
        ExchangeRate exchangeRate = exchangeRateMapper.toEntity(exchangeRateDTO);
        exchangeRate = exchangeRateRepository.save(exchangeRate);
        return exchangeRateMapper.toDto(exchangeRate);
    }

    @Override
    public Optional<ExchangeRateDTO> partialUpdate(ExchangeRateDTO exchangeRateDTO) {
        log.debug("Request to partially update ExchangeRate : {}", exchangeRateDTO);

        return exchangeRateRepository
            .findById(exchangeRateDTO.getId())
            .map(existingExchangeRate -> {
                exchangeRateMapper.partialUpdate(existingExchangeRate, exchangeRateDTO);

                return existingExchangeRate;
            })
            .map(exchangeRateRepository::save)
            .map(exchangeRateMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExchangeRateDTO> findAll() {
        log.debug("Request to get all ExchangeRates");
        return exchangeRateRepository.findAll().stream().map(exchangeRateMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExchangeRateDTO> findOne(Long id) {
        log.debug("Request to get ExchangeRate : {}", id);
        return exchangeRateRepository.findById(id).map(exchangeRateMapper::toDto);
    }

    @Override
    public List<ExchangeRateDTO> findByBaseAndTimestampGreaterThanOrEqualOrderByTimestampDesc(String base, Instant startOfPeriod) {
        return exchangeRateRepository
            .findByBaseAndTimestampGreaterThanEqualOrderByTimestampDesc(base, startOfPeriod)
            .stream()
            .map(exchangeRateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExchangeRate : {}", id);
        exchangeRateRepository.deleteById(id);
    }
}
