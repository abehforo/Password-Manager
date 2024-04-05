import java.util.Scanner;

public class AccountManager {
    // shift value for the encryption method 
    private static final int SHIFT = 3; 

    public static void main(String[] args) {
        // Loading the user data 
        FileIO.loadUserData(); 

        Scanner scanner = new Scanner(System.in);
//showing the initial menu
        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
//checking for an int response
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); 
//using switch cases to either create acount, login or exit and save user data
                switch (choice) {
                    case 1:
                        FileIO.createAccount(scanner);
                        break;
                    case 2:
                        FileIO.login(scanner);
                        break;
                    case 3:
                        FileIO.saveUserData(); // Save user data before exiting
                        System.out.println("Exiting program. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } else {
                System.out.println("Incorrect input. Please enter a valid option.");
                scanner.nextLine(); // consume the invalid input
            }
        }
    }
}
