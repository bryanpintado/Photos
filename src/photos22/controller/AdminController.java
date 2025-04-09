package photos22.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.event.ActionEvent;
import photos22.User; // Import the User class
import photos22.UserManager;
import photos22.AlertUtil;

public class AdminController {

    UserManager manager = new UserManager();

    @FXML
    private ListView<User> userListView;

    @FXML
    private void initialize() {
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
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            AlertUtil.showAlert("Error: no user selected");
        }
        manager.deleteUser(selectedUser.getUsername());

    }

    @FXML
    private void handleLogout(ActionEvent event) {
        return;
    }
}