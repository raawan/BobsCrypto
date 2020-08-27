package portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BitcoinPortfolioTest {

    @DisplayName("Given a valid file input with one bitcoin, should return total by multiplying units to the single bitcoin value ")
    @Test
    public void shouldReturnTotalValueOfSinleBitcoin() {

        BitcoinPortfolio bitcoinPortfolio = new BitcoinPortfolio(new ClientApi(),
                new FileApi("src/test/resources/bobs_crypto_test_onebitcoin.txt"));

        final var totalValueOfEachBitcoin = bitcoinPortfolio.calculateValueOfEachBitcoin();

        assertEquals(1, totalValueOfEachBitcoin.size());
        assertTrue(totalValueOfEachBitcoin.get("BTC").doubleValue()!=0);
    }

    @DisplayName("Given a valid file input with many bitcoin, should return total for each bitcoin by multiplying units to the single bitcoin value ")
    @Test
    public void shouldReturnTotalValueOfAllBitcoinsInAFile() {

        BitcoinPortfolio bitcoinPortfolio = new BitcoinPortfolio(new ClientApi(),
                new FileApi("src/test/resources/bobs_crypto_test.txt"));

        final var totalValueOfEachBitcoin = bitcoinPortfolio.calculateValueOfEachBitcoin();

        assertEquals(3, totalValueOfEachBitcoin.size());
        assertTrue(totalValueOfEachBitcoin.get("BTC").doubleValue()!=0);
        assertTrue(totalValueOfEachBitcoin.get("ETH").doubleValue()!=0);
        assertTrue(totalValueOfEachBitcoin.get("ATOM").doubleValue()!=0);

    }


}
