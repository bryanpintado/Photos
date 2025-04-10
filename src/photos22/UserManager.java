package photos22;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserManager {
    private static final String storeDir = "data";
    private static final String storeFile = "users.dat";

    private static UserManager instance;
    public ObservableList<User> users;

    private UserManager() {
        users = FXCollections.observableArrayList();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void deleteUser(String username) {
        if (!isUserInList(username)) {
            AlertUtil.showAlert("Error: username (" + username + ") is not in the list");
            return;
        }
        for (User user : users) {
            if (user.getUsername().equals(username)) {
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

    public void saveUsers() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(new ArrayList<>(users));
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public void loadUsers() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir + File.separator + storeFile));
        ArrayList<User> loadedUsers = (ArrayList<User>) ois.readObject();
        users.setAll(loadedUsers);
        ois.close();
    }
}