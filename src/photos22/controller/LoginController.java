package photos22.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;  // This links to the TextField with fx:id="usernameField" in the FXML.

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            System.out.println("Please enter a username.");

            return;
        }
        
        // Test print
        System.out.println("Logged in as: " + username);
        
    }
}