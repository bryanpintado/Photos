package photos22.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photos22.SceneUtil;
import photos22.User;
import photos22.UserManager;
import photos22.AlertUtil;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            AlertUtil.showAlert("Error: empty username");

            return;
        }
        if (username.equals("admin")) {
            SceneUtil.switchScene((Stage) usernameField.getScene().getWindow(), "admin.fxml",
                    "Admin Photo Application");
        } else {
            User user = UserManager.getInstance().getUserByUsername(username);
            if (user == null) {
                AlertUtil.showAlert("Error: user does not exist");
                return;
            }

            UserController controller = SceneUtil.switchSceneWithController(
                    (Stage) usernameField.getScene().getWindow(),
                    "user_home.fxml",
                    "Photo Application");

            if (controller != null) {
                controller.setUser(user);
            }
        }

        // Test print
        System.out.println("Logged in as: " + username);

    }
}
