package portfolio;

import java.util.List;
import java.util.Map;

public class BitcoinPortfolio {

    private final ClientApi clientApi;
    private final FileApi fileApi;

    public BitcoinPortfolio(ClientApi clientApi, FileApi fileApi) {
        this.clientApi = clientApi;
        this.fileApi = fileApi;
    }

    public double calculatePortfolioValue(Map<String, Double> totalValuePerBitcoin) {
        return 0.0;
    }

    public Map<String, Double> calculateBitcoinValue(List<String> fileInput) {
        return null;
    }
}
