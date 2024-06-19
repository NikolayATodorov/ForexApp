package com.forex.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.forex.test.service.impl.ExchangeRatesUpdaterImpl;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestClient;

public class ExchangeRatesUpdaterTest {

    @Test
    public void testCallRestEndpoint_Success() throws Exception {
        // Mock URL and HttpURLConnection
        URL url = Mockito.mock(URL.class);
        HttpURLConnection conn = Mockito.mock(HttpURLConnection.class);
        when(url.openConnection()).thenReturn(conn);

        // Mock InputStreamReader and BufferedReader
        InputStreamReader isr = Mockito.mock(InputStreamReader.class);
        BufferedReader br = Mockito.mock(BufferedReader.class);
        when(br.readLine()).thenReturn("response", (String) null);

        // Set up the connection to return a 200 OK response code
        when(conn.getResponseCode()).thenReturn(200);

        // Call the method under test
        ExchangeRatesUpdater client = new ExchangeRatesUpdaterImpl();
        String result = client.updateExchangeRates();

        // Verify the result
        assertEquals("response", result);

        // Verify interactions
        verify(conn).setRequestMethod("GET");
        verify(conn).getResponseCode();
        verify(conn).getInputStream();
    }

    @Test
    public void testCallRestEndpoint_Failure() throws Exception {
        // Mock URL and HttpURLConnection
        URL url = Mockito.mock(URL.class);
        HttpURLConnection conn = Mockito.mock(HttpURLConnection.class);
        when(url.openConnection()).thenReturn(conn);

        // Set up the connection to return a non-200 response code
        when(conn.getResponseCode()).thenReturn(404);

        // Call the method under test
        ExchangeRatesUpdater client = new ExchangeRatesUpdaterImpl();
        String result = client.updateExchangeRates();

        // Verify the result
        assertEquals("GET request not worked, Response Code: 404", result);

        // Verify interactions
        verify(conn).setRequestMethod("GET");
        verify(conn).getResponseCode();
    }

    @Test
    public void testCallRestEndpoint_Exception() {
        // Call the method under test with an invalid URL
        ExchangeRatesUpdater client = new ExchangeRatesUpdaterImpl();
        String result = client.setAPI_KEY("").updateExchangeRates();

        // Verify the result
        assertTrue(result.startsWith("Exception: "));
    }
}
