import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthTester {

    public static String testPasswordStrength(String password) {

        int length = password.length();

// Checking for uppercase letter
        boolean hasUppercase = !password.equals(password.toLowerCase());

// Checking  for any special characters using regex
        Pattern specialCharPattern = Pattern.compile("[!@#$%^&*()-_=+]");
        Matcher matcher = specialCharPattern.matcher(password);
        boolean hasSpecialChar = matcher.find();
//different conditions have to be met for the password meter to give you a different score
        if (length < 8) {
            return "Poor";
        } else if (length >= 8 && length <= 12 && hasUppercase) {
            return "Fair";
        } else if (length >= 13 && length <= 16 && hasUppercase && hasSpecialChar) {
            return "Good";
        } else if (length > 16 && hasUppercase && hasSpecialChar && password.matches(".*[a-z].*") && password.matches(".*\\d.*")) {
            return "Excellent";
        } else {
            return "Poor"; 
        }
    }
}
