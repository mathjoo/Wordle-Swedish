package src;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main {

    static ArrayList<String> list = new ArrayList<>();

    static ArrayList<String> read() throws Exception {
        File file = new java.io.File("../lib/fem_bokstaver.txt");
        java.util.Scanner fileScanner = new Scanner(file);

        // Read the file and add each line to the list

        while (fileScanner.hasNextLine()) {
            list.add(fileScanner.nextLine().trim());
        }
        fileScanner.close();
        return list;
    }

    public static void main(String[] args) throws Exception {
        ArrayList<String> list = read();

        int ran = (int) (Math.random() * list.size());
        String word = list.get(ran);
        System.out.println(word);

        // read a five letter word from terminal
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Skriv in ett ord med fem bokstäver:");
        String input = scanner.nextLine();
        while (true) {
            if (input.length() != 5) {
                System.out.println("Ordet måste vara fem bokstäver långt. Försök igen:");
                input = scanner.nextLine();
            } else if (!list.contains(input)) {
                System.out.println("Inte ett riktigt ord. Försök igen:");
                input = scanner.nextLine();
            } else if (input.equals(word)) {
                System.out.println("Rätt ord! Du gissade " + input);
                break;
            } else {
                System.out.println("Fel ord, försök igen:");
                input = scanner.nextLine();
            }
        }
        while ((input.length() != 5) || (!list.contains(input))) {
            System.out.println("Ogiltigt ord, försök igen:");
            input = scanner.nextLine();
        }
        scanner.close();
    }
}
