package photos22;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserManager {
    public ObservableList<User> users;

    public UserManager() {
        users = FXCollections.observableArrayList();
    }

    public void deleteUser(String username) {
        if (isUserInList(username) == false) {
            AlertUtil.showAlert("Error: username (" + username + ") is not in the list");
        }
    }

    public boolean isUserInList(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}