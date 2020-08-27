package portfolio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import portfolio.exception.FileReadException;

public class FileApi {

    private final String fileLocation;

    public FileApi(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public List<String> getLines() {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation))) {
            stream.forEach(line -> lines.add(line));
        } catch (IOException e) {
            throw new FileReadException("Error reading file");
        }
        return lines;
    }
}
