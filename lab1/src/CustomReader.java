import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CustomReader implements Searchable {

    private List<String> lines;

    public CustomReader(String filepath) {
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
        int low = 0, high = this.lines.size();

        while (low <= high) {
            int mid = (low + high) / 2;
            if (this.lines.get(mid).compareTo(word) < 0) {
                low = mid + 1;
            } else if (this.lines.get(mid).compareTo(word) > 0) {
                high = mid - 1;
            } else if (this.lines.get(mid).compareTo(word) == 0) {
                return true;
            }
        }
        return false;
//        for(String line : this.lines) {
//            if(line.equals(word)) return true;
//        }
//        return false;
    }
}
