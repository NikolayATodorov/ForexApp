package com.forex.test.service;

import com.forex.test.service.dto.ExchangeRateDTO;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.forex.test.domain.ExchangeRate}.
 */
public interface ExchangeRateService {
    /**
     * Save a exchangeRate.
     *
     * @param exchangeRateDTO the entity to save.
     * @return the persisted entity.
     */
    ExchangeRateDTO save(ExchangeRateDTO exchangeRateDTO);

    /**
     * Updates a exchangeRate.
     *
     * @param exchangeRateDTO the entity to update.
     * @return the persisted entity.
     */
    ExchangeRateDTO update(ExchangeRateDTO exchangeRateDTO);

    /**
     * Partially updates a exchangeRate.
     *
     * @param exchangeRateDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ExchangeRateDTO> partialUpdate(ExchangeRateDTO exchangeRateDTO);

    /**
     * Get all the exchangeRates.
     *
     * @return the list of entities.
     */
    List<ExchangeRateDTO> findAll();

    /**
     * Get the "id" exchangeRate.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExchangeRateDTO> findOne(Long id);

    /**
     * Get the "base" exchangeRate.
     *
     * @param base the base of the entity.
     * @return the entity.
     */
    //    Optional<ExchangeRateDTO> findByBaseAndLastByTimestampOrderByTimestamp(String base);

    /**
     * Get all the exchangeRates with specified base and after startOfPeriod.
     *
     * @return the list of entities.
     */
    List<ExchangeRateDTO> findByBaseAndTimestampGreaterThanOrEqualOrderByTimestampDesc(String base, Instant startOfPeriod);

    /**
     * Delete the "id" exchangeRate.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
