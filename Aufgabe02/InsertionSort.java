import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

public class InsertionSort {
    private static final String user = "Lars";
    //    private static String[] emails = {"Patrick", "Miles", "Milka", "Marco", "Lars"};
    private static final String[] alleNoten = {"1.0", "1.3", "1.5", "1.7", "2.0", "2.0", "2.3",
            "2.5", "2.7", "3.0", "3.3", "3.5", "3.7", "4.0"};

//    private static String[][] listeErstellen(String[] emails){
//        String[][] liste = new String[emails.length][2];
//        for (int i = 0; i < emails.length; i++) {
//            liste[i][0] = emails[i];
//            if (emails[i].equals(user)) liste[i][1] = noteEinlesen();
//            else liste[i][1] = alleNoten[new Random().nextInt(alleNoten.length)];
//        }
//        return liste;
//    }

    private static String[][] listeErstellenAusDatei(String pfad) throws IOException {
        Path path = Paths.get(pfad);
        Scanner countSc = new Scanner(path);
        Scanner scanner = new Scanner(path);

        int n = 0;
        while (countSc.hasNext()) {
            countSc.nextLine();
            n++;
        }
        String[][] liste = new String[n][2];

        for (int i = 0; i < n; i++) {
            String email = scanner.next();
            liste[i][0] = email;
            if (email.equals(user)) liste[i][1] = noteEinlesen();
            else liste[i][1] = alleNoten[new Random().nextInt(alleNoten.length)];
        }
        return liste;
    }

    public static String noteEinlesen() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Eigene Note eingeben: ");
        return inputScanner.next();
//        TODO: checken ob Eingabe in alleNoten enthalten ist
    }

    public static void sort(String[][] liste) {
        int N = liste.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (liste[j - 1][1].compareTo(liste[j][1]) > 0) {
//                    exchange [1]
                    String tempNote = liste[j - 1][1];
                    liste[j - 1][1] = liste[j][1];
                    liste[j][1] = tempNote;
//                    exchange [0]
                    String tempEmail = liste[j - 1][0];
                    liste[j - 1][0] = liste[j][0];
                    liste[j][0] = tempEmail;
                } else break;
            }
        }
    }

    public static void ausgabe(String[][] liste) {
        System.out.println("------- NOTENLISTE -------");
        for (int i = 0; i < liste.length; i++) {
            System.out.printf("Student: %10s@htwg-konstanz.de  |   Note: %s%n", liste[i][0], liste[i][1]);
        }
    }

    public static void main(String[] args) throws IOException {
//        String[][] liste = listeErstellen(emails);
        String[][] liste = listeErstellenAusDatei("Aufgabe02/emails.txt");
        sort(liste);
        ausgabe(liste);
    }
}
