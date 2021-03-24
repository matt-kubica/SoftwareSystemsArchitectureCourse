import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.function.Function;


public class Main {

    public static final String FILENAME = "places.txt";
    public static final int TESTS_AMOUNT = 100000;

    public static String getRandomWord() {
        Path path = Paths.get(FILENAME);
        List<String> lines = null;

        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert lines != null;
        int pos = new Random().nextInt(lines.size());
        return lines.get(pos);
    }

    public static long measure(String toFind, Function<String, Boolean> function) {
        long start = System.currentTimeMillis();
        boolean found = function.apply(toFind);
        long finish = System.currentTimeMillis();
        long result = finish - start;

        // System.out.println(String.format("Method finds word '%s' in %d [ms]", toFind, result));
        assert found;
        return result;
    }


    public static void main(String[] args) {
        Function <String, Boolean> wholeReader = (toFind) -> new WholeReader(FILENAME).search(toFind);
        Function <String, Boolean> lineByLineReader = (toFind) -> new LineByLineReader(FILENAME).search(toFind);
        Function <String, Boolean> customReader = (toFind) -> new CustomReader(FILENAME).search(toFind);


        for(int i = 10; i <= TESTS_AMOUNT; i *= 10) {
            int j = 0;
            long sumOne = 0, sumTwo = 0, sumThree = 0;

            while(j++ < i) {
                String word = Main.getRandomWord();
                sumOne += measure(word, wholeReader);
                sumTwo += measure(word, lineByLineReader);
                sumThree += measure(word, customReader);
            }

            System.out.println(String.format("For %d test cases...", j - 1));
            System.out.println(String.format("\t...it takes %f on average for method '%s' to find a word...", (float)sumOne / j, "wholeReader"));
            System.out.println(String.format("\t...it takes %f on average for method '%s' to find a word...", (float)sumTwo / j, "lineByLineReader"));
            System.out.println(String.format("\t...it takes %f on average for method '%s' to find a word...", (float)sumThree / j, "customReader"));
        }


    }
}
