package photos22;

public class User {
    public String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

    public String getUsername() {
        return username;
    }
}