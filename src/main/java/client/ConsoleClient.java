package client;

import portfolio.BitcoinPortfolio;
import portfolio.ClientApi;
import portfolio.FileApi;

public class ConsoleClient {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter a valid path to file");
            return;
        }

        BitcoinPortfolio bitcoinPortfolio = new BitcoinPortfolio(new ClientApi(), new FileApi(args[0]));
        bitcoinPortfolio.printResult();
    }
}
