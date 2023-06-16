

public class Graph {
    private int nodeCount;
    private int edgeCount;
    private Bag[] adj;
    public Graph(int nodeCount){
        this.nodeCount = nodeCount;
        this.edgeCount = 0;
        this.adj = new Bag[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            this.adj[i] = new Bag();
        }
    }
    public void setNames(String[] names){
        for (int i = 0; i < names.length; i++) {
            this.adj[i].setName(names[i]);
        }
    }
    public void addEdge(int v, int w, int weight){
        this.edgeCount++;
        adj[v-1].add(w-1, weight);
        adj[w-1].add(v-1, weight);
    }
    public void printGraph(){
        System.out.println("GRAPH _______");
        System.out.println("NodeCount: " + this.nodeCount);
        System.out.println("EdgeCount: " + this.edgeCount);
        System.out.println();
        for (Bag bag : adj) {
            bag.printBag();
        }
    }
}
