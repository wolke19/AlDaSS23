import java.util.Scanner;


public class EmergencyQueue {
    public static class Incident {
        public int emergencyLevel;
        public String name;

        public Incident(int emergencyLevel, String callerName) {
            this.emergencyLevel = emergencyLevel;
            this.name = callerName;
        }
    }

    private final Incident[] queue;
    private int len;

    public EmergencyQueue(int capacity) {
        queue = new Incident[capacity + 1];
    }

    int delMax() {
        int root = queue[1].emergencyLevel;
        swap(1, len--);
        queue[len + 1] = null;
        swapDown(1);
        return root;
    }

    public void enqueue(int emergencyLevel, String callerName) {
        //Todo: handle cases when queue cap is reached
        queue[++len] = new Incident(emergencyLevel, callerName);
        swapUp(len);
    }

    public boolean isEmpty() {
        return len == 0;
    }

    public void swapUp(int k) {
        while (k > 1 && queue[k / 2].emergencyLevel < queue[k].emergencyLevel) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void swapDown(int k) {
        while (2 * k <= len) {
            int j = 2 * k;
            if (j < len && queue[j].emergencyLevel < queue[j + 1].emergencyLevel) j++;
            if (queue[k].emergencyLevel >= queue[j].emergencyLevel) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int x1, int x2) {
        Incident temp = queue[x2];
        queue[x2] = queue[x1];
        queue[x1] = temp;
    }

    public static void printHeap(EmergencyQueue heap, int len) {
        int hoehe = (int) Math.ceil((Math.log(15) / Math.log(2)) - 1);
        int breite = (int) Math.pow(2, hoehe);
        System.out.println("------- STATS -------");
        System.out.println("Höhe: " + hoehe);
        System.out.println("Breite: " + breite);
        System.out.println("------- HEAP -------");
        for (int i = 0; i <= hoehe; i++) {
//            if (i%2 == 0) System.out.print("          ");
            printEmpty(2 * breite / Math.pow(2, i + 1));
            int startElement = (int) Math.pow(2, i);
            int endElement = (int) Math.pow(2, i + 1) - 1;
            for (int j = startElement; j <= endElement; j++) {
                if (j <= len) {
                    System.out.printf("%-10s", heap.queue[j].emergencyLevel);
                    switch (i) {
                        case 1 -> printEmpty(7);
                        case 2 -> printEmpty(3);
                        case 3 -> printEmpty(1);
                    }
                }
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

    public static void printEmpty(double count) {
        for (int i = 0; i < (int) count; i++) {
            System.out.print("          ");
        }
    }

    public static void main(String[] args) {
        int cap = 100;
        EmergencyQueue campusFestivalSOS = new EmergencyQueue(cap);


        while (true) {
            System.out.println("action? (0 for enqueue, 1 for dequeue, 2 for quit");
            Scanner scanner = new Scanner(System.in);
            int action = scanner.nextInt();
            switch (action) {
                case 0 -> {
                    System.out.println("enter em. lvl");
                    int newIncEmergency = scanner.nextInt();
//                    System.out.println("enter caller name");
//                    String newIncName = scanner.next();
                    if (campusFestivalSOS.len >= cap) System.out.println("QUEUE CAPACITY REACHED! CRASHING NOW");
                    campusFestivalSOS.enqueue(newIncEmergency, "name");
                    System.out.println("Incident queued");
                }
                case 1 -> {
                    if (campusFestivalSOS.len == 0) System.out.println("NO ELEMENT TO DELETE! CRAHSING NOW :)");
                    campusFestivalSOS.delMax();
                    System.out.println("highest em. lvl. Inc. done");
                    System.out.println("remove.....");
                    System.out.println("....");
                }
                case 2 -> {
                    System.out.println("shutting down....");
                    System.out.println("bye");
                    return;
                }
                case 3 -> {
                    for (int i = 0; i < cap; i++) {
                        campusFestivalSOS = new EmergencyQueue(cap);
                    }
                    for (int i = 0; i < cap; i++) {
                        campusFestivalSOS.enqueue(1, "NA");
                    }
                }
            }
            printHeap(campusFestivalSOS, campusFestivalSOS.len);
        }
    }
}
