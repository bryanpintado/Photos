package photos22.controller;

import java.io.IOException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import photos22.model.Album;
import photos22.model.User;
import photos22.model.UserManager;
import photos22.util.AlertUtil;
import photos22.util.SceneUtil;

public class UserController {
    private User user;
    @FXML
    private ListView<Album> albumListView;

    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
    }

    @FXML
    private void handleCreateAlbum() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create Album");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter new album name:");

        Optional<String> result = dialog.showAndWait();
        if (!result.isPresent())
            return;

        String name = result.get().trim();
        if (name.isEmpty()) {
            AlertUtil.showAlert("Album name cannot be empty.");
            return;
        }

        for (Album a : user.getAlbums()) {
            if (a.getName().equalsIgnoreCase(name)) {
                AlertUtil.showAlert("An album with that name already exists.");
                return;
            }
        }

        user.getAlbums().add(new Album(name));
        UserManager.getInstance().saveUsers();

        albumListView.setItems(FXCollections.observableArrayList(user.getAlbums()));
    }

    @FXML
    private void handleDeleteAlbum() {
        Album selected = albumListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showAlert("Please select an album to delete.");
            return;
        }

        user.getAlbums().remove(selected);
        UserManager.getInstance().saveUsers();
        albumListView.setItems(FXCollections.observableArrayList(user.getAlbums()));
    }

    @FXML
    private void handleOpenAlbum() {
        Album selected = albumListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showAlert("Please select an album to open.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/photos22/view/album_view.fxml"));
            Parent root = loader.load();

            AlbumController controller = loader.getController();
            controller.setAlbum(selected);

            Stage stage = (Stage) albumListView.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Album: " + selected.getName());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            AlertUtil.showAlert("Failed to load album view.\n" + e.toString());
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        UserManager.getInstance().saveUsers();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        SceneUtil.switchScene(stage, "login.fxml", "Login");
    }

    public void setUser(User user) {
        this.user = user;
        albumListView.setItems(FXCollections.observableArrayList(user.getAlbums()));
        welcomeLabel.setText("Welcome, " + user.getUsername());
    }
    @FXML
private void handleSearchPhotos() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/photos22/view/search_view.fxml"));
        Parent root = loader.load();

        SearchController controller = loader.getController();
        controller.setUser(user);

        Stage stage = (Stage) albumListView.getScene().getWindow();
        stage.setTitle("Search Photos");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        AlertUtil.showAlert("Failed to open search screen.\n" + e.getMessage());
    }
}
}