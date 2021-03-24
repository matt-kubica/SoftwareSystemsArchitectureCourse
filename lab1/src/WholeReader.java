import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WholeReader implements Searchable {

    private List<String> lines;

    public WholeReader(String filepath) {
        Path path = Paths.get(filepath);

        try {
            this.lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayFileContent() {
        assert this.lines != null;
        this.lines.forEach(System.out::println);
    }

    public boolean search(String word) {
        // this.lines.stream().filter(l -> l.equals(word)).findFirst().orElse(null);

        for(String line : this.lines) {
            if(line.equals(word)) return true;
        }
        return false;
    }
}
