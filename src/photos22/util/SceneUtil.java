package photos22.util;

import java.io.IOException;

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
    public static <T> T switchSceneWithController(Stage stage, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneUtil.class.getResource("/photos22/view/" + fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
            return loader.getController(); 
        } catch (IOException e) {
            AlertUtil.showAlert("Failed to load: " + fxmlFile + "\n" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
