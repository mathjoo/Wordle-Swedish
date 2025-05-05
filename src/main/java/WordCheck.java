import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCheck {
    private final ArrayList<String> list = new ArrayList<>();
    private final String word;

    public WordCheck() throws Exception {
        read();
        int ran = (int) (Math.random() * list.size());
        this.word = list.get(ran);
    }

    private void read() throws Exception {
        File file = new File("lib/fem_bokstaver.txt");
        Scanner fileScanner = new Scanner(file);

        while (fileScanner.hasNextLine()) {
            list.add(fileScanner.nextLine().trim());
        }
        fileScanner.close();
    }

    public String getWord() {
        return word;
    }

    public List<String> getList() {
        return List.copyOf(list);
    }

    public boolean contains(String guessedWord) {
        return list.contains(guessedWord);
    }

    public boolean addWord(String word) throws IOException {
        word = word.trim().toLowerCase();
        if (word.length() == 5 && word.matches("[a-zåäö]{5}")) {
            Path path = Path.of("lib/fem_bokstaver.txt");
            Files.write(path, List.of(word), java.nio.file.StandardOpenOption.APPEND);
            list.add(word); // uppdatera även i minnet
            return true;
        }
        return false;
    }
}
