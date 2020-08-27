package portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FileApiTest {

    @Test
    public void shouldReturnAllFileDataAsStringPerLine() {

        FileApi fileApi = new FileApi("resources/bobs_crypto_test.txt");
        final var lines = fileApi.getLines();
        assertNotNull(lines);
        assertEquals(3, lines.size());
    }

}
