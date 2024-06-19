package com.forex.test.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CurrentExchangeRatesJsonRequest.
 */
@Entity
@Table(name = "current_exchange_rates_json_request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CurrentExchangeRatesJsonRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "request_id", nullable = false, unique = true)
    private String requestId;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @NotNull
    @Column(name = "client", nullable = false)
    private String client;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CurrentExchangeRatesJsonRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public CurrentExchangeRatesJsonRequest requestId(String requestId) {
        this.setRequestId(requestId);
        return this;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public CurrentExchangeRatesJsonRequest timestamp(Instant timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getClient() {
        return this.client;
    }

    public CurrentExchangeRatesJsonRequest client(String client) {
        this.setClient(client);
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCurrency() {
        return this.currency;
    }

    public CurrentExchangeRatesJsonRequest currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrentExchangeRatesJsonRequest)) {
            return false;
        }
        return getId() != null && getId().equals(((CurrentExchangeRatesJsonRequest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CurrentExchangeRatesJsonRequest{" +
            "id=" + getId() +
            ", requestId='" + getRequestId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", client='" + getClient() + "'" +
            ", currency='" + getCurrency() + "'" +
            "}";
    }
}
