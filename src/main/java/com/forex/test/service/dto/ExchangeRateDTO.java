package com.forex.test.service.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.forex.test.domain.ExchangeRate} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExchangeRateDTO implements Serializable {

    private Long id;

    @NotNull
    private String base;

    @NotNull
    private Instant timestamp;

    private BigDecimal usd;

    private BigDecimal gbp;

    private BigDecimal chf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getUsd() {
        return usd;
    }

    public void setUsd(BigDecimal usd) {
        this.usd = usd;
    }

    public BigDecimal getGbp() {
        return gbp;
    }

    public void setGbp(BigDecimal gbp) {
        this.gbp = gbp;
    }

    public BigDecimal getChf() {
        return chf;
    }

    public void setChf(BigDecimal chf) {
        this.chf = chf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExchangeRateDTO)) {
            return false;
        }

        ExchangeRateDTO exchangeRateDTO = (ExchangeRateDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, exchangeRateDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExchangeRateDTO{" +
            "id=" + getId() +
            ", base='" + getBase() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", usd=" + getUsd() +
            ", gbp=" + getGbp() +
            ", chf=" + getChf() +
            "}";
    }
}
