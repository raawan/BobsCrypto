package portfolio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import portfolio.exception.InvalidInputException;

class ClientApiTest {

    @Test
    void shouldReturnEURvalueForETHbitcoin() {
        ClientApi clientApi = new ClientApi();
        String bitCoinValue = clientApi.getBitCoinValue("ETH", "EUR");
        assertNotNull(bitCoinValue);
        assertTrue(bitCoinValue.contains("EUR"));
        assertTrue(new BigDecimal(extractBitcoinValueFromResponse(bitCoinValue))
                .compareTo(new BigDecimal(0)) > 0);

    }

    @Test
    void shouldReturnGBPvalueForBTCbitcoin() {
        ClientApi clientApi = new ClientApi();
        String bitCoinValue = clientApi.getBitCoinValue("BTC", "GBP");
        assertNotNull(bitCoinValue);
        assertTrue(bitCoinValue.contains("GBP"));
        assertTrue(new BigDecimal(extractBitcoinValueFromResponse(bitCoinValue))
                .compareTo(new BigDecimal(0)) > 0);

    }

    @Test
    void shouldThrowInvalidInputExceptionForInvalidCurrency() {
        ClientApi clientApi = new ClientApi();

        Exception exception = assertThrows(
                InvalidInputException.class,
                () -> clientApi.getBitCoinValue("BTC", "EURo"));

        assertTrue(exception.getMessage().contains("Invalid Input"));

    }

    private String extractBitcoinValueFromResponse(String bitCoinValue) {
        //Example response format--> {"EUR":324.12}
        return bitCoinValue.substring(bitCoinValue.indexOf(":") + 1, bitCoinValue.length() - 1);
    }
}
