import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Banan");
        list.add("Bilar");
        list.add("Cykel");
        list.add("Drake");
        list.add("Ekorre");
        list.add("Fiske");
        list.add("Glada");
        list.add("Husky");
        list.add("Jolle");
        list.add("Knapp");

        int ran = (int) (Math.random() * list.size());
        String word = list.get(ran);

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
