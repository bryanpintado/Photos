package photos22.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import photos22.User; // Import the User class
import photos22.UserManager;
import photos22.AlertUtil;
public class AdminController {

    @FXML
    private ListView<User> userListView;
    private ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        userListView.setItems(users);
    }

    @FXML
    private void handleCreateUser(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter new username");
        String username = dialog.showAndWait().orElse(null);
        if (username == null || username.trim().isEmpty()) {
            AlertUtil.showAlert("Error: Username cannot be empty");
            return;
        }

        if (isUserInList(username)) {
            AlertUtil.showAlert("Error: username (" + username + ") already in list");
            return;
        }
        User newUser = new User(username.trim());
        users.add(newUser);
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        return;
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        return;
    }
}