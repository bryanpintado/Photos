package photos22.controller;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        System.out.println("Create Album clicked");
    }

    @FXML
    private void handleDeleteAlbum() {
        // TODO: implement logic
        System.out.println("Delete Album clicked");
    }

    @FXML
    private void handleOpenAlbum() {
        // TODO: implement logic
        System.out.println("Open Album clicked");
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
}