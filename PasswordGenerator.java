import java.security.SecureRandom;
//the class responsible for making a password
public class PasswordGenerator {
// strings that contain all possible elements in a password 
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+";
//takes in the int length (the pass length the user asked for)
    public static String generateStrongPassword(int length) {
        String combinedChars = UPPER + LOWER + DIGITS + SPECIAL;
        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder(length);
        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        for (int i = 4; i < length; i++) {
            password.append(combinedChars.charAt(random.nextInt(combinedChars.length())));
        }

        return password.toString();
    }
}
