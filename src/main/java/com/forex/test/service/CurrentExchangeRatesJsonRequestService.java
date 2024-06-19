package com.forex.test.service;

import com.forex.test.service.dto.CurrentExchangeRatesJsonRequestDTO;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.forex.test.domain.CurrentExchangeRatesJsonRequest}.
 */
public interface CurrentExchangeRatesJsonRequestService {
    /**
     * Save a currentExchangeRatesJsonRequest.
     *
     * @param currentExchangeRatesJsonRequestDTO the entity to save.
     * @return the persisted entity.
     */
    CurrentExchangeRatesJsonRequestDTO save(CurrentExchangeRatesJsonRequestDTO currentExchangeRatesJsonRequestDTO);

    /**
     * Get all the currentExchangeRatesJsonRequests.
     *
     * @return the list of entities.
     */
    List<CurrentExchangeRatesJsonRequestDTO> findAll();

    /**
     * Get the "id" currentExchangeRatesJsonRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CurrentExchangeRatesJsonRequestDTO> findOne(Long id);

    /**
     * Get the "requestId" currentExchangeRatesJsonRequest.
     *
     * @param requestId the id of the entity.
     * @return the entity.
     */
    Optional<CurrentExchangeRatesJsonRequestDTO> findByRequestId(String requestId);

    /**
     * Delete the "id" currentExchangeRatesJsonRequest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
