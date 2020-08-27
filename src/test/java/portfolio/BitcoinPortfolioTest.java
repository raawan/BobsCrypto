package portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class BitcoinPortfolioTest {

    @DisplayName("Given a valid file input with many bitcoin, " +
            "should return total for each bitcoin by multiplying units to the single bitcoin value ")
    @Test
    void shouldReturnTotalValueOfAllBitcoinsInAFile() {

        BitcoinPortfolio bitcoinPortfolio = new BitcoinPortfolio(new ClientApi(),
                new FileApi("src/test/resources/bobs_crypto_test.txt"));

        final var totalValueOfEachBitcoin = bitcoinPortfolio.calculateValueOfEachBitcoin();

        assertEquals(3, totalValueOfEachBitcoin.size());
        assertNotEquals(0, totalValueOfEachBitcoin.get("BTC").doubleValue());
        assertNotEquals(0, totalValueOfEachBitcoin.get("ETH").doubleValue());
        assertNotEquals(0, totalValueOfEachBitcoin.get("ATOM").doubleValue());

    }

    @DisplayName("should return the sum total of the bitcoin portfolio")
    @Test
    void shouldReturnThePortfolioValue() {

        BitcoinPortfolio bitcoinPortfolio = new BitcoinPortfolio(new ClientApi(),
                new FileApi("src/test/resources/bobs_crypto_test.txt"));

        final var totalPortfolioValue = bitcoinPortfolio.calculatePortfolioValue();
        assertNotEquals(0, totalPortfolioValue.doubleValue());

    }

}
