package portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitcoinPortfolio {

    private final ClientApi clientApi;
    private final FileApi fileApi;

    public BitcoinPortfolio(ClientApi clientApi, FileApi fileApi) {
        this.clientApi = clientApi;
        this.fileApi = fileApi;
    }

    public ClientApi getClientApi() {
        return clientApi;
    }

    public FileApi getFileApi() {
        return fileApi;
    }

    public double calculatePortfolioValue(Map<String, Double> totalValuePerBitcoin) {
        return 0.0;
    }

    public Map<String, BigDecimal> calculateBitcoinTotalValue(List<String> fileInput) {
        return fileInput
                .stream()
                .collect(
                        HashMap::new,
                        (map, line) -> map.put(line.trim().substring(0, line.indexOf("=")), calculateBitcoinValue(line)),
                        Map::putAll
                );
    }

    private BigDecimal calculateBitcoinValue(String line) {
        String bitCoinValue = clientApi.getBitCoinValue(line.trim().substring(0, line.indexOf("=")), "EUR");
        final var result = new BigDecimal(bitCoinValue.substring(bitCoinValue.indexOf(":") + 1, bitCoinValue.length() - 1))
                .multiply(new BigDecimal(Integer.valueOf(line.trim().substring(line.indexOf("=") + 1, line.length()))));
        return result.setScale(2, RoundingMode.CEILING);
    }
}
