package com.forex.test.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HistoryExchangeRatesJsonRequest.
 */
@Entity
@Table(name = "history_exchange_rates_json_request")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HistoryExchangeRatesJsonRequest implements Serializable {

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

    @NotNull
    @Column(name = "period", nullable = false)
    private Integer period;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public HistoryExchangeRatesJsonRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public HistoryExchangeRatesJsonRequest requestId(String requestId) {
        this.setRequestId(requestId);
        return this;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public HistoryExchangeRatesJsonRequest timestamp(Instant timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getClient() {
        return this.client;
    }

    public HistoryExchangeRatesJsonRequest client(String client) {
        this.setClient(client);
        return this;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCurrency() {
        return this.currency;
    }

    public HistoryExchangeRatesJsonRequest currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getPeriod() {
        return this.period;
    }

    public HistoryExchangeRatesJsonRequest period(Integer period) {
        this.setPeriod(period);
        return this;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoryExchangeRatesJsonRequest)) {
            return false;
        }
        return getId() != null && getId().equals(((HistoryExchangeRatesJsonRequest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistoryExchangeRatesJsonRequest{" +
            "id=" + getId() +
            ", requestId='" + getRequestId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", client='" + getClient() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", period=" + getPeriod() +
            "}";
    }
}
