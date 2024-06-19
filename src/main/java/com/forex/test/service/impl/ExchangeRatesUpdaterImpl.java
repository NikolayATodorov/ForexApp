package com.forex.test.service.impl;

import com.forex.test.service.ExchangeRatesUpdater;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRatesUpdaterImpl implements ExchangeRatesUpdater {

    @Value(value = "${spring.API_KEY}")
    String API_KEY;

    @Value(value = "${spring.base}")
    String base;

    @Value(value = "${spring.symbols}")
    String symbols;

    public String getAPI_KEY() {
        return API_KEY;
    }

    public ExchangeRatesUpdater setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
        return this;
    }

    @Scheduled(fixedRateString = "${spring.exchange-rate-update-interval}" + "000")
    public String updateExchangeRates() {
        String endpointUrl = "https://data.fixer.io/api/latest?access_key=" + API_KEY + "&base=" + base + "&symbols=" + symbols;

        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(endpointUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) { // 200 OK
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } else {
                result.append("GET request not worked, Response Code: ").append(responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception: " + e.getMessage();
        }
        return result.toString();
    }
}
