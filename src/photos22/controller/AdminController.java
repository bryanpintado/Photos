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
            showAlert("Error: Username cannot be empty");
            return;
        }

        if (isUserInList(username)) {
            showAlert("Error: username ("+ username + ") already in list");
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

    private boolean isUserInList(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}