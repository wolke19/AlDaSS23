import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class WeightedQU {

    public int[] id;
    private int[] sz;

    public WeightedQU(int N) {
        id = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }


    public int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }

    public void union( int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;
        else if (sz[pRoot] < sz[qRoot]) {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }
        else {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    public boolean connected (int p, int q){
        return (find(p) == find(q));
    }

    public static void createConnections (WeightedQU uf) throws IOException {
        Path pathP = Paths.get("Aufgabe02/p.txt");
        Path pathQ = Paths.get("Aufgabe02/q.txt");

        Scanner psc = new Scanner(pathP);
        Scanner qsc = new Scanner(pathQ);

        while (psc.hasNextInt() && qsc.hasNextInt()){
            int p = psc.nextInt();
            int q = qsc.nextInt();
            uf.union(p, q);
            System.out.printf("connected %d with %d %n", p, q);
            System.out.println("New Id-Array: " + Arrays.toString(uf.id));
            System.out.println("Size p: " + uf.sz[p]);
            System.out.println("Size q: " + uf.sz[q]);
            System.out.println("sind sie verbunden? " + uf.connected(p,q));
            System.out.println();
        }

        psc.close();
        qsc.close();
    }

    public static void main(String[] args) throws IOException {
        WeightedQU uf = new WeightedQU(11);
        createConnections(uf);

        Path citiesPath = Paths.get("Aufgabe02/cities.txt");
        Scanner citieScanner = new Scanner(citiesPath);
        String[] cities = new String[11];
        for (int i = 0; i < 11; i++){
            cities[i] = citieScanner.nextLine();
        }

        int actionHandler = 0;
        Scanner scanner = new Scanner(System.in);


        while (actionHandler == 0){
            System.out.println("Verbindungsanfrage. Bitte Zwei Städte auswählen.");
            for (int i = 0; i < cities.length; i++){
                System.out.printf("(%d) für %s%n", i, cities[i]);
            }

//        Verbindungsanfrage


            int pIn = scanner.nextInt();
            int qIn = scanner.nextInt();

            if (uf.connected(pIn, qIn)) System.out.println("Verbindung vorhanden!");
            else {
                uf.union(pIn, qIn);
                System.out.println("Verbindung jetzt angelegt.");
            }


            System.out.println("Weitere Verbindung anfragen? (0) für JA, (1) für NEIN.");
            actionHandler = scanner.nextInt();
        }
        scanner.close();
    }
}
