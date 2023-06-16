public class Main {
    public static void main(String[] args) {
        String testEmail = "test888test@htwg-konstanz.de";
        System.out.println(Hash.hashHTWGUsername(testEmail));
        Hash.hashHTWGUsernameWithLogs(testEmail);

        System.out.println();
        System.out.println();
        System.out.println("INITIALISING NEW GRAPH........");
        Graph containerMap = new Graph(6);
        containerMap.setNames(new String[]{"1_Orange", "2_Purple", "3_Blue", "4_Green", "5_Yellow", "6_Red"});
        containerMap.addEdge(1,2,4);
        containerMap.addEdge(1,3,4);
        containerMap.addEdge(1,5,2);
        containerMap.addEdge(1,6,5);
        containerMap.addEdge(2,4,5);
        containerMap.addEdge(2,6,2);
        containerMap.addEdge(3,5,4);
        containerMap.addEdge(4,6,3);
        containerMap.addEdge(5,6,5);
        System.out.println("GRAPH READY.........");
        containerMap.printGraph();
    }
}
