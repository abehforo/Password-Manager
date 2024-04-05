import java.util.HashMap;
import java.util.Map;
// using inheritance for user 
public class User extends Account {
    //initilizing the password 
    private String password;
    private Map<String, AppData> appData;
//setting up basic info for a user 
    public User(String username, String password) {
        super(username);
        this.password = password;
        this.appData = new HashMap<>();
    }
// returning user data
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, AppData> getAppData() {
        return appData;
    }

    public void setAppData(Map<String, AppData> appData) {
        this.appData = appData;
    }
//adding data for app  
    public void addAppData(String appName, String appUsername, String appPassword) {
        appData.put(appName, new AppData(appUsername, appPassword));
    }
// method for editing user data regarding apps
    public void editAppData(String oldAppName, String newAppName, String newAppUsername, String newAppPassword) {
        AppData updatedAppData = new AppData(newAppUsername, newAppPassword);
        appData.put(newAppName.isEmpty() ? oldAppName : newAppName, updatedAppData);
        if (!oldAppName.equals(newAppName)) {
            appData.remove(oldAppName);
        }
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }
}
