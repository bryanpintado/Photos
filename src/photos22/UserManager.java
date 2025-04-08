package photos22;

import java.util.ArrayList;

public class UserManager {
    ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public void deleteUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.remove(i);
                break;
            }
        }
    }

}