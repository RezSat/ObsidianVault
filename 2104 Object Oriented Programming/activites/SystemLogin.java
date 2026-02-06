public class SystemLogin {
    public static void main(String[] args) {
        boolean isLoggedIn = true;
        boolean isAccountActive = true;

        if (isLoggedIn && isAccountActive) {
            System.out.println("Access granted.");
        } else {
            System.out.println("Access denied.");
        }
    }
}
