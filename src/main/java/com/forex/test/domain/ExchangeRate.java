package com.forex.test.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ExchangeRate.
 */
@Entity
@Table(name = "exchange_rate")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "base", nullable = false)
    private String base;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "usd", precision = 21, scale = 2)
    private BigDecimal usd;

    @Column(name = "gbp", precision = 21, scale = 2)
    private BigDecimal gbp;

    @Column(name = "chf", precision = 21, scale = 2)
    private BigDecimal chf;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ExchangeRate id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return this.base;
    }

    public ExchangeRate base(String base) {
        this.setBase(base);
        return this;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public ExchangeRate timestamp(Instant timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getUsd() {
        return this.usd;
    }

    public ExchangeRate usd(BigDecimal usd) {
        this.setUsd(usd);
        return this;
    }

    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }

    public BigDecimal getGbp() {
        return this.gbp;
    }

    public ExchangeRate gbp(BigDecimal gbp) {
        this.setGbp(gbp);
        return this;
    }

    public void setGbp(BigDecimal gbp) {
        this.gbp = gbp;
    }

    public BigDecimal getChf() {
        return this.chf;
    }

    public ExchangeRate chf(BigDecimal chf) {
        this.setChf(chf);
        return this;
    }

    public void setChf(BigDecimal chf) {
        this.chf = chf;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExchangeRate)) {
            return false;
        }
        return getId() != null && getId().equals(((ExchangeRate) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExchangeRate{" +
            "id=" + getId() +
            ", base='" + getBase() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", usd=" + getUsd() +
            ", gbp=" + getGbp() +
            ", chf=" + getChf() +
            "}";
    }
}
