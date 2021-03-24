import java.io.*;
import java.nio.charset.StandardCharsets;

public class LineByLineReader implements Searchable {

    private BufferedReader bufferedReader;

    public LineByLineReader(String filepath) {
        try {
            this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean search(String word) {
        String current;
        try {
            while((current = bufferedReader.readLine()) != null) {
                if(current.equals(word)) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
