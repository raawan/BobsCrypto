package portfolio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ClientApiTest {

    @Test
    void shouldReturnEURvalueForETHbitcoin() throws IOException, InterruptedException {
        ClientApi clientApi = new ClientApi();
        String bitCoinValue = clientApi.getBitCoinValue("ETH", "EUR");
        assertNotNull(bitCoinValue);
        assertTrue(bitCoinValue.contains("EUR"));
        assertTrue(new BigDecimal(extractBitcoinValueFromResponse(bitCoinValue))
                .compareTo(new BigDecimal(0)) > 0);

    }

    @Test
    void shouldReturnGBPvalueForBTCbitcoin() throws IOException, InterruptedException {
        ClientApi clientApi = new ClientApi();
        String bitCoinValue = clientApi.getBitCoinValue("BTC", "GBP");
        assertNotNull(bitCoinValue);
        assertTrue(bitCoinValue.contains("GBP"));
        assertTrue(new BigDecimal(extractBitcoinValueFromResponse(bitCoinValue))
                .compareTo(new BigDecimal(0)) > 0);

    }

    private String extractBitcoinValueFromResponse(String bitCoinValue) {
        //Example response format--> {"EUR":324.12}
        return bitCoinValue.substring(bitCoinValue.indexOf(":") + 1, bitCoinValue.length() - 1);
    }
}
