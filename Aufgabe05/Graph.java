public class Graph {
    private final int nodeCount;
    private int edgeCount;
    private final Bag[] adj;

    public Graph(int nodeCount) {
        this.nodeCount = nodeCount;
        this.edgeCount = 0;
        this.adj = new Bag[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            this.adj[i] = new Bag();
        }
    }

    public void setNames(String[] names) {
        for (int i = 0; i < names.length; i++) {
            this.adj[i].setName(names[i]);
        }
    }

    public void addEdge(int v, int w, int weight) {
        this.edgeCount++;
        adj[v].add(w, weight);
        adj[w].add(v, weight);
    }

    public void printGraph() {
        System.out.println("GRAPH _______");
        System.out.println("NodeCount: " + this.nodeCount);
        System.out.println("EdgeCount: " + this.edgeCount);
        System.out.println();
        for (Bag bag : adj) {
            bag.printBagVer2();
        }
    }
}
