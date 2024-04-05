import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileIO {
    private static Map<String, User> users = new HashMap<>();
    private static String currentUser = null;
    private static final int SHIFT = 3;
//method that lets user create account and checks if the user already exists
    public static void createAccount(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose another.");
            return;
        }

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.put(username, newUser);

        System.out.println("Account created successfully.");
    }
//login method that checks if the user is not laready registererd as well
    public static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        if (!users.containsKey(username)) {
            System.out.println("Username not found. Please create an account first.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = username;
// Second menu for user after logging in 
            while (true) {
    System.out.println("\nWelcome, " + currentUser + "!");
    System.out.println("1. Manage Apps");
    System.out.println("2. Change Password");
    System.out.println("3. Log Out");
    System.out.print("Enter your choice: ");
// checking for a correct input
    if (scanner.hasNextInt()) {
        int choice = scanner.nextInt();
        scanner.nextLine(); 
// switch cases that lead to manageApps or let the user change their account password or log out
        switch (choice) {
            case 1:
                manageApps(scanner);
                break;
            case 2:
                changePassword(scanner);
                break;
            case 3:
                currentUser = null;
                System.out.println("Logged out successfully.");
                return;
            default:
                System.out.println("Invalid choice. Please enter a valid option.");
        }
    } else {
        System.out.println("Incorrect input. Please enter a valid option.");
        scanner.nextLine(); 
    }
}

        } else {
            System.out.println("Incorrect password. Please try again.");
        }
    }
// method that lets user add/modify apps and manage passwords 
    private static void manageApps(Scanner scanner) {
    while (true) {
        System.out.println("\n1. Add App");
        System.out.println("2. Edit App Data");
        System.out.println("3. View Apps and Data");
        System.out.println("4. Generate Strong Password");
        System.out.println("5. Password Strength Test");
        System.out.println("6. Delete App");  // New option to delete an app
        System.out.println("7. Go Back");
        System.out.print("Enter your choice: ");
//checking for correct input (int)
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline
//using switch cases based of user input
            switch (choice) {
                case 1:
                    addApp(scanner);
                    break;
                case 2:
                    editAppData(scanner);
                    break;
                case 3:
                    viewAppsAndData();
                    break;
                case 4:
                    generateStrongPassword(scanner);
                    break;
                case 5:
                    passwordStrengthTest(scanner);
                    break;
                case 6:
                    AppManager.deleteApp(users.get(currentUser), scanner);   // Call the new method to delete an app
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } else {
            System.out.println("Incorrect input. Please enter a valid option.");
            scanner.nextLine(); // consume the invalid input
        }
    }
}

// method that lets the user add any app (name,username,pass)
    public static void addApp(Scanner scanner) {
        System.out.print("Enter the app name: ");
        String appName = scanner.nextLine();

        System.out.print("Enter the app username: ");
        String appUsername = scanner.nextLine();

        System.out.print("Enter the app password: ");
        String appPassword = scanner.nextLine();
//cheking for user 
        User user = users.get(currentUser);
        if (user != null) {
            user.addAppData(appName, appUsername, appPassword);
            System.out.println("App data added successfully.");
        } else {
            System.out.println("User not found.");
        }
    }
//method for letting the user edit Apps 
    public static void editAppData(Scanner scanner) {
        System.out.print("Enter the app name to edit data: ");
        String appName = scanner.nextLine();

        User user = users.get(currentUser);
 //checking for user and if the app already exists so that it can be edited
        if (user != null && user.getAppData().containsKey(appName)) {
            System.out.println("Current App Data:");
            System.out.println("App Name: " + appName);
            System.out.println("App Username: " + user.getAppData().get(appName).getUsername());
            System.out.println("App Password: " + user.getAppData().get(appName).getPassword());

            System.out.print("Enter the new app name: ");
            String newAppName = scanner.nextLine();

            System.out.print("Enter the new app username: ");
            String newAppUsername = scanner.nextLine();

            System.out.print("Enter the new app password: ");
            String newAppPassword = scanner.nextLine();

            user.editAppData(appName, newAppName, newAppUsername, newAppPassword);

            System.out.println("App data updated successfully.");
        } else {
            System.out.println("App not found.");
        }
    }
//method showing all the data for the apps 
    public static void viewAppsAndData() {
        User user = users.get(currentUser);
        if (user != null) {
            System.out.println("\nApps and Data:");
            for (Map.Entry<String, AppData> entry : user.getAppData().entrySet()) {
                System.out.println("App Name: " + entry.getKey());
                System.out.println("App Username: " + entry.getValue().getUsername());
                System.out.println("App Password: " + entry.getValue().getPassword());
                System.out.println("________________________________________________________________________________");
            }
        } else {
            System.out.println("User not found.");
        }
    }
//method allwoing the user to change password
    public static void changePassword(Scanner scanner) {
        System.out.print("Enter your current password: ");
        String currentPassword = scanner.nextLine();
//checking if old password matches
        User user = users.get(currentUser);
        if (user != null && user.getPassword().equals(currentPassword)) {
            System.out.print("Enter your new password: ");
            String newPassword = scanner.nextLine();

            user.setPassword(newPassword);

            System.out.println("Password changed successfully.");
        } else {
            System.out.println("Incorrect current password. Password change failed.");
        }
    }
//reading data from user_accounts.txt to load
    public static void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //sending parts to decrypt 
                String[] parts = line.split(",");
                String username = decrypt(parts[0]);
                String password = decrypt(parts[1]);

                User user = new User(username, password);
                users.put(username, user);

                // Loading the app data
                Map<String, AppData> appData = new HashMap<>();
                try (BufferedReader appReader = new BufferedReader(new FileReader(username + "_apps.txt"))) {
                    String appLine;
                    while ((appLine = appReader.readLine()) != null) {
                        String[] appParts = appLine.split(",");
                        String appName = decrypt(appParts[0]);
                        String appUsername = decrypt(appParts[1]);
                        String appPassword = decrypt(appParts[2]);
                        appData.put(appName, new AppData(appUsername, appPassword));
                    }
                }
                user.setAppData(appData);
            }
        } catch (IOException e) {
            // error catch
        }
    }
//method that saves the data for the user onto txt file 
    public static void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_accounts.txt"))) {
            for (User user : users.values()) {
                writer.write(encrypt(user.getUsername()) + "," + encrypt(user.getPassword()));
                writer.newLine();

                // Saves the app data
                try (BufferedWriter appWriter = new BufferedWriter(new FileWriter(user.getUsername() + "_apps.txt"))) {
                    Map<String, AppData> appData = user.getAppData();
                   
//going over appData by iterating and encrypting 
 if (appData != null) {
                        for (Map.Entry<String, AppData> appEntry : appData.entrySet()) {
                            appWriter.write(
                                    encrypt(appEntry.getKey()) + "," +
                                            encrypt(appEntry.getValue().getUsername()) + "," +
                                            encrypt(appEntry.getValue().getPassword())
                            );
                            appWriter.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//encrypting method (Ceasar's cypher)
    private static String encrypt(String input) {
    StringBuilder encrypted = new StringBuilder();
    for (char c : input.toCharArray()) {
        if (Character.isLetter(c)) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            char shifted = (char) ((c - base + SHIFT) % 26 + base);
            encrypted.append(shifted);
        } else {
            encrypted.append(c);
        }
    }
    return encrypted.toString();
}
//decrypting method (Ceasar's cypher)
    private static String decrypt(String input) {
    StringBuilder decrypted = new StringBuilder();
    for (char c : input.toCharArray()) {
        if (Character.isLetter(c)) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            char shifted = (char) ((c - base - SHIFT + 26) % 26 + base);
            decrypted.append(shifted);
        } else {
            decrypted.append(c);
        }
    }
    return decrypted.toString();
}
//this method calls PasswordGenerator.java to make a pass   
    private static void generateStrongPassword(Scanner scanner) {
        System.out.print("Enter the desired length for the password: ");
        int length = scanner.nextInt();
        scanner.nextLine(); // consume the newline

        String strongPassword = PasswordGenerator.generateStrongPassword(length);
        System.out.println("Generated Strong Password: " + strongPassword);
    }
// calls passwordStrengthTest to test a password     
    private static void passwordStrengthTest(Scanner scanner) {
    System.out.print("Enter the password to test: ");
    String password = scanner.nextLine();

    String strength = PasswordStrengthTester.testPasswordStrength(password);
    System.out.println("Password Strength: " + strength);
}
}



