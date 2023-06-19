public class Main {
    public static void main(String[] args) {

//        ___________ LE 10 _________________________
        String testEmail = "test888test@htwg-konstanz.de";
        System.out.println(Hash.hashHTWGUsername(testEmail));
        hashHTWGUsernameWithLogs(testEmail);

//        ___________ LE 11 _________________________
        System.out.println();
        System.out.println();
        System.out.println("INITIALISING NEW GRAPH........");
        Graph containerMap = new Graph(6);
        containerMap.setNames(new String[]{"0_Orange", "1_Purple", "2_Blue", "3_Green", "4_Yellow", "5_Red"});
        containerMap.addEdge(0,1,4);
        containerMap.addEdge(0,2,4);
        containerMap.addEdge(0,4,2);
        containerMap.addEdge(0,5,5);
        containerMap.addEdge(1,3,5);
        containerMap.addEdge(1,5,2);
        containerMap.addEdge(2,4,4);
        containerMap.addEdge(3,5,3);
        containerMap.addEdge(4,5,5);
        System.out.println("GRAPH READY.........");
        System.out.println();

        containerMap.printGraph();
    }

    public static void hashHTWGUsernameWithLogs(String email){
        System.out.println("______ LOGS __________________________________________________________");
        System.out.println("Email: " + email);
        String username = email.trim().split("@")[0];
        System.out.println("username: " + username);
        username.chars().forEach(ch -> System.out.println((char) ch + ":" + ch));
        System.out.println("Summe: " + username.chars().sum());
        System.out.println("x13: " + username.chars().sum() * 13);
    }
}
