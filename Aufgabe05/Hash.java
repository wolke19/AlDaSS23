public class Hash {
    public static int hashHTWGUsername(String email) {
        return email.trim().split("@")[0].chars().sum() * 13;
    }
}
