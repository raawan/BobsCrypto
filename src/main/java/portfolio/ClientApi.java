package portfolio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import portfolio.exception.InvalidInputException;
import portfolio.exception.RestClientException;

public class ClientApi {

    public String getBitCoinValue(String bitcoin, String currency) {

        String uri = new StringBuilder()
                .append("https://min-api.cryptocompare.com/data/price?fsym=")
                .append(bitcoin)
                .append("&tsyms=")
                .append(currency)
                .toString();

        HttpResponse<String> response;
        try {
            response = getHttpClient().send(getHttpRequest(uri), HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RestClientException("Error communicating REST api");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RestClientException("Error communicating REST api");
        }

        final var body = response.body();
        if (body.contains("Error")) {
            throw new InvalidInputException("Invalid Input");
        }
        return body;
    }

    private static HttpRequest getHttpRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }

    private static HttpClient getHttpClient() {
        return HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    }
}
