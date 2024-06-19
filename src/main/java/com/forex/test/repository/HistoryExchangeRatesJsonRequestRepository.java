package com.forex.test.repository;

import com.forex.test.domain.HistoryExchangeRatesJsonRequest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HistoryExchangeRatesJsonRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoryExchangeRatesJsonRequestRepository extends JpaRepository<HistoryExchangeRatesJsonRequest, Long> {
    Optional<HistoryExchangeRatesJsonRequest> findByRequestId(String requestId);
}
