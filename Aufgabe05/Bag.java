public class Bag {
    private String name;
    private Edge first;
    private int n;

    public Bag() {
        this.first = null;
        this.n = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(int dest, int weight) {
        Edge temp = this.first;
        this.first = new Edge(dest, weight);
        this.first.next = temp;
        this.n++;
    }

    public void printBag() {
        System.out.println("NODE: " + this.name);
        System.out.println("N: " + this.n);
        Edge iterator = this.first;
        while (iterator != null) {
            iterator.printEdge();
            iterator = iterator.next;
        }
        System.out.println();
    }

    private class Edge {
        private final int dest;
        private final int weight;
        private Edge next;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }

        public void printEdge() {
            System.out.println("Goes to: " + this.dest + "   Weight: " + this.weight);
        }
    }

}
