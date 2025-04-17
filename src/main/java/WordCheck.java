import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCheck {
    public static ArrayList<String> list = new ArrayList<>();
    public String word;

    public WordCheck() throws Exception {
        if (list.isEmpty()) {
            read();
        }
        int ran = (int) (Math.random() * list.size());
        word = list.get(ran);
    }

    static void read() throws Exception {
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

    public static ArrayList<String> getList() {
        return list;
    }

    public boolean contains(String guessedWord) {
        if (list.contains(guessedWord)) {
            return true;
        }
        return false;
    }
}
