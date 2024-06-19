package com.forex.test.repository;

import com.forex.test.domain.CurrentExchangeRatesJsonRequest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CurrentExchangeRatesJsonRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrentExchangeRatesJsonRequestRepository extends JpaRepository<CurrentExchangeRatesJsonRequest, Long> {
    Optional<CurrentExchangeRatesJsonRequest> findByRequestId(String requestId);
}
