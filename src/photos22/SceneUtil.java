package photos22;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneUtil {
    public static void switchScene(Stage stage, String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(SceneUtil.class.getResource("/photos22/view/" + fxmlFile));
            stage.setScene(new Scene(root, 400, 300));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) {
            AlertUtil.showAlert("Failed to load scene: " + fxmlFile + "\n" + e);
            e.printStackTrace();
        }
    }
}
