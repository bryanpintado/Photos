package photos22.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import photos22.UserManager; // Import the User class
import photos22.User;
import photos22.AlertUtil;
import photos22.SceneUtil;

public class AdminController {

    UserManager manager = UserManager.getInstance();

    @FXML
    private ListView<User> userListView;

    @FXML
    private void initialize() {
        try {
            manager.loadUsers();

        } catch (Exception e) {
            AlertUtil.showAlert("Error: " + e.toString());
        }
        userListView.setItems(manager.users);
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
        if (manager.isUserInList(username)) {
            AlertUtil.showAlert("Error: username (" + username + ") already in list");
            return;
        }
        User newUser = new User(username.trim());
        manager.users.add(newUser);

        try {
            manager.saveUsers();
        } catch (IOException e) {
            AlertUtil.showAlert("Error saving users: " + e.toString());
        }
        // try {
        // manager.loadUsers();
        // } catch (Exception e) {
        // AlertUtil.showAlert("Error: " + e.toString());
        // }
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            AlertUtil.showAlert("Error: no user selected");
            return;
        }
        manager.deleteUser(selectedUser.getUsername());
        try {
            manager.saveUsers();
        } catch (IOException e) {
            AlertUtil.showAlert("Error saving users: " + e.toString());
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneUtil.switchScene((Stage) userListView.getScene().getWindow(), "login.fxml", "Login Page");
    }
}