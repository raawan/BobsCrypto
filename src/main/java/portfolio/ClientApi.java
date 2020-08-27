package portfolio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ClientApi {

    public String getBitCoinValue(String bitcoin, String currency) throws IOException, InterruptedException {

        String uri = new StringBuilder()
                .append("https://min-api.cryptocompare.com/data/price?fsym=")
                .append(bitcoin)
                .append("&tsyms=")
                .append(currency)
                .toString();

        final var httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

        final var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }
}
