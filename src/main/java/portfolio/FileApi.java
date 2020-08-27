package portfolio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileApi {

    private final String fileLocation;

    public FileApi(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<String> getLines() throws IOException {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation))) {
            stream.forEach(line -> lines.add(line));
        }
        return lines;
    }
}
