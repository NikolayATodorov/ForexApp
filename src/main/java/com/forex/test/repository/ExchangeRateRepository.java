package com.forex.test.repository;

import com.forex.test.domain.ExchangeRate;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ExchangeRate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    //    List<ExchangeRate> findByBaseAndTimestampGreaterThanOrEqualAndByOrderByTimestampDesc(String base, Instant startOfPeriod);
    List<ExchangeRate> findByBaseAndTimestampGreaterThanEqualOrderByTimestampDesc(String base, Instant startOfPeriod);
}
