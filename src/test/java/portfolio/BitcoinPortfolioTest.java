package portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BitcoinPortfolioTest {


    @Test
    public void shouldReturnTotalValueOfEachBitcoin() {

        BitcoinPortfolio bitcoinPortfolio = new BitcoinPortfolio(new ClientApi(),
                new FileApi("src/test/resources/bobs_crypto_test_onebitcoin.txt"));

        final var totalValueOfEachBitcoin = bitcoinPortfolio.calculateBitcoinTotalValue(bitcoinPortfolio.getFileApi().getLines());

        assertEquals(1, totalValueOfEachBitcoin.size());
        assertTrue(totalValueOfEachBitcoin.get("BTC") != 0);
    }

}
