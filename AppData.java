public class AppData extends Account {
    private String username;
    private String password;

    public AppData(String username, String password) {
        //calling superclass (Account)
        super(username);
        // Initializing the username and password
        this.username = username; 
        this.password = password;
    }
// getPassword method returns the pass 
    public String getPassword() {
        return password;
    }
}
