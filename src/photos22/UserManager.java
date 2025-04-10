package photos22;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserManager {
    private static UserManager instance;
    public ObservableList<User> users;

    private UserManager() {
        users = FXCollections.observableArrayList();
    }

    public static UserManager getInstance(){
        if(instance == null){
            instance = new UserManager();
        }
        return instance;
    }

    public void deleteUser(String username) {
        if (!isUserInList(username)) {
            AlertUtil.showAlert("Error: username (" + username + ") is not in the list");
            return;
        }
        for(User user : users){
            if(user.getUsername().equals(username)){
                users.remove(user);
                break;
            }
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