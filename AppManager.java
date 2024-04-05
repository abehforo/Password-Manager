import java.util.Map;
import java.util.Scanner;
//Deleting already existing apps
public class AppManager {
    public static void deleteApp(User user, Scanner scanner) {
        System.out.print("Enter the app name to delete: ");
        String appNameToDelete = scanner.nextLine();

        if (user != null && user.getAppData().containsKey(appNameToDelete)) {
            user.getAppData().remove(appNameToDelete);
            System.out.println("App and its data deleted successfully.");
        } else {
            System.out.println("App not found.");
        }
    }
}
