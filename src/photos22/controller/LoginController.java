package photos22.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import photos22.*;
public class LoginController {

    @FXML
    private TextField usernameField; // This links to the TextField with fx:id="usernameField" in the FXML.

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            AlertUtil.showAlert("Error: empty username");

            return;
        }
       SceneUtil.switchScene((Stage) usernameField.getScene().getWindow(), "admin.fxml", "Admin Photo Application");

        // Test print
        System.out.println("Logged in as: " + username);

    }

}