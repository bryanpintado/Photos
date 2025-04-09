package photos22.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

import javafx.event.ActionEvent;
import photos22.User; // Import the User class
import photos22.UserManager;

public class AdminController {

    @FXML
    private ListView<User> userListView;

    @FXML
    private void handleCreateUser(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Enter new username");
        String username = dialog.showAndWait().orElse(null);
        if (username.trim().isEmpty() || username == null) {
            showAlert("Error: Username cannot be empty");
        }
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        return;
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        return;
    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
