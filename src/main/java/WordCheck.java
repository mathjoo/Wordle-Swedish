import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCheck {
    public final ArrayList<String> list = new ArrayList<>();
    private String word;

    public WordCheck() throws Exception {
        read();
        pickNewWord();
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

    public String pickNewWord() {
        int ran = (int) (Math.random() * list.size());
        word = list.get(ran);
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
            list.add(word); 
            return true;
        }
        return false;
    }
}