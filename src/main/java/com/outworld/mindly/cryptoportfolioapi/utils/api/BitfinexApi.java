package com.outworld.mindly.cryptoportfolioapi.utils.api;

import com.google.gson.JsonObject;
import com.outworld.mindly.cryptoportfolioapi.exceptions.ApiLimitException;
import com.outworld.mindly.cryptoportfolioapi.models.CryptoCurrency;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class BitfinexApi {

    private static final String URL = "https://api-pub.bitfinex.com/v2/calc/fx";

    private static final String EURO = "EUR";

    public BigDecimal getExchangeRate(CryptoCurrency currency) {
        JsonObject body = new JsonObject();
        body.addProperty("ccy1", currency.getSymbol());
        body.addProperty("ccy2", EURO);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString())).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new BigDecimal(response.body().replaceAll("[\\[\\]]", ""));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (NumberFormatException e) {
            throw new ApiLimitException();
        }
    }
}
