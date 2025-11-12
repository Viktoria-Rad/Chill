import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList<Movie>watched = new ArrayList<>();
    private ArrayList<Movie>watchLater = new ArrayList<>();

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }

    @Override
    public String toString() {
        return (username + " , " + password);
    }
}
