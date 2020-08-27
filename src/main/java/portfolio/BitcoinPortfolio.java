package portfolio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class BitcoinPortfolio {

    public static final String EUR = "EUR";
    private final ClientApi clientApi;
    private final FileApi fileApi;

    public BitcoinPortfolio(ClientApi clientApi, FileApi fileApi) {
        this.clientApi = clientApi;
        this.fileApi = fileApi;
    }

    public void printResult() {
        printLine();
        final var bitcoinValueMap = calculateValueOfEachBitcoin();
        bitcoinValueMap.forEach((key, value) -> System.out.println(key + ":" + value));
        printLine();
        System.out.println("TOTAL:" + calculatePortfolioValue(bitcoinValueMap));
        printLine();
    }

    public BigDecimal calculatePortfolioValue(final Map<String, BigDecimal> bitcoinValueMap) {
        return bitcoinValueMap
                .values()
                .stream()
                .reduce(new BigDecimal("0"), BigDecimal::add);
    }

    public Map<String, BigDecimal> calculateValueOfEachBitcoin() {
        return fileApi.getLines()
                .stream()
                .filter(line -> !(line.isEmpty() || line.isBlank()))
                .collect(
                        HashMap::new,
                        (map, line) -> map.put(getBitcoinName(line), calculateBitcoinValue(line)),
                        Map::putAll
                );
    }

    private BigDecimal calculateBitcoinValue(String line) {
        String bitCoinValue = clientApi.getBitCoinValue(getBitcoinName(line), EUR);
        final var result = new BigDecimal(extractBitcoinValueFromResponse(bitCoinValue))
                .multiply(new BigDecimal(getBitCoinUnits(line)));
        return result.setScale(2, RoundingMode.CEILING);
    }

    private String getBitcoinName(String line) {
        return line.trim().substring(0, line.indexOf("="));
    }

    private Integer getBitCoinUnits(String line) {
        return Integer.valueOf(line.trim().substring(line.indexOf("=") + 1, line.length()));
    }

    private String extractBitcoinValueFromResponse(String bitCoinValue) {
        //Example response format--> {"EUR":324.12}
        return bitCoinValue.substring(bitCoinValue.indexOf(":") + 1, bitCoinValue.length() - 1);
    }

    private void printLine() {
        System.out.println("-------------------");
    }

}
