package com.forex.test.service;

public interface ExchangeRatesUpdater {
    String updateExchangeRates();

    ExchangeRatesUpdater setAPI_KEY(String API_KEY);
}
