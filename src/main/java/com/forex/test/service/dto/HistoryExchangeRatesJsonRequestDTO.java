package com.forex.test.service.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.forex.test.domain.HistoryExchangeRatesJsonRequest} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistoryExchangeRatesJsonRequestDTO implements Serializable {

    private Long id;

    @NotNull
    private String requestId;

    @NotNull
    private Instant timestamp;

    @NotNull
    private String client;

    @NotNull
    private String currency;

    @NotNull
    private Integer period;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoryExchangeRatesJsonRequestDTO)) {
            return false;
        }

        HistoryExchangeRatesJsonRequestDTO historyExchangeRatesJsonRequestDTO = (HistoryExchangeRatesJsonRequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, historyExchangeRatesJsonRequestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoryExchangeRatesJsonRequestDTO{" +
            "id=" + getId() +
            ", requestId='" + getRequestId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", client='" + getClient() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", period=" + getPeriod() +
            "}";
    }
}
