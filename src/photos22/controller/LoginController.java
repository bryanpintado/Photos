package photos22.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField; // This links to the TextField with fx:id="usernameField" in the FXML.

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        if (username.isEmpty()) {
            showAlert("Error: empty username");

            return;
        }
        if (username.equals("admin") || username.equals("Admin")) {
            switchScene("admin.fxml");
        }

        // Test print
        System.out.println("Logged in as: " + username);

    }

    private void showAlert(String message) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // helper method to switch scenes
    private void switchScene(String fxmlFile) {
        // Load the login screen FXML file from the view folder
        if (fxmlFile.equals("admin.fxml")) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/photos22/view/" + fxmlFile));
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setTitle("Admin Photo Application");
                stage.setScene(new Scene(root, 400, 300));
                stage.show();
            } catch (Exception e) {
                showAlert("Failed to load " + fxmlFile + "\n" + "Reason: " + e.toString());
            }
        }
    }
}