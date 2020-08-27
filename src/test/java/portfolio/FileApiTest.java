package portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FileApiTest {

    @Test
    void shouldReturnAllFileDataAsStringPerLine() {

        FileApi fileApi = new FileApi("src/test/resources/bobs_crypto_test.txt");
        final var lines = fileApi.getLines();
        assertNotNull(lines);
        assertEquals("ETH=5", lines.stream().filter(line -> line.equalsIgnoreCase("ETH=5")).findAny().get());
        assertEquals("BTC=10", lines.stream().filter(line -> line.equalsIgnoreCase("BTC=10")).findAny().get());
        assertEquals("ATOM=13", lines.stream().filter(line -> line.equalsIgnoreCase("ATOM=13")).findAny().get());
    }

}
