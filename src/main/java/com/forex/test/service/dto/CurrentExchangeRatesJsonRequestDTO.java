package com.forex.test.service.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.forex.test.domain.CurrentExchangeRatesJsonRequest} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CurrentExchangeRatesJsonRequestDTO implements Serializable {

    private Long id;

    @NotNull
    private String requestId;

    @NotNull
    private Instant timestamp;

    @NotNull
    private String client;

    @NotNull
    private String currency;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrentExchangeRatesJsonRequestDTO)) {
            return false;
        }

        CurrentExchangeRatesJsonRequestDTO currentExchangeRatesJsonRequestDTO = (CurrentExchangeRatesJsonRequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, currentExchangeRatesJsonRequestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CurrentExchangeRatesJsonRequestDTO{" +
            "id=" + getId() +
            ", requestId='" + getRequestId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", client='" + getClient() + "'" +
            ", currency='" + getCurrency() + "'" +
            "}";
    }
}
