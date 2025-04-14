package photos22.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import photos22.model.User;
import photos22.model.UserManager;
import photos22.util.AlertUtil;
import photos22.util.SceneUtil;

public class AdminController {

    UserManager manager = UserManager.getInstance();

    @FXML
    private ListView<User> userListView;

    @FXML
    private void initialize() {
        manager.loadUsers();
        userListView.setItems(manager.getUsers());
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
        manager.addUser(newUser);

        manager.saveUsers();
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            AlertUtil.showAlert("Error: no user selected");
            return;
        }
        manager.deleteUser(selectedUser.getUsername());
        manager.saveUsers();
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneUtil.switchScene((Stage) userListView.getScene().getWindow(), "login.fxml", "Login Page");
    }
}