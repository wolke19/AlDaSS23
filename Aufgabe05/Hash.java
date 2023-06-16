public class Hash {
    public static int hashHTWGUsername(String email){
        return email.trim().split("@")[0].chars().sum() * 13;
    }
    public static void hashHTWGUsernameWithLogs(String email){
        System.out.println("______ LOGS __________________________________________________________");
        String username = email.trim().split("@")[0];
        System.out.println("username: " + username);
        username.chars().forEach(ch -> System.out.println((char) ch + ":" + ch));
        System.out.println("Summe: " + username.chars().sum());
        System.out.println("x13: " + username.chars().sum() * 13);
    }
}
