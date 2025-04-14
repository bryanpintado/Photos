package photos22.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import photos22.model.Album;
import photos22.model.User;

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
        // TODO: implement logic
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
    private void handleLogout() {
        // TODO: implement logic
        System.out.println("Logout clicked");
    }

    public void setUser(User user) {
        this.user = user;
        albumListView.setItems(FXCollections.observableArrayList(user.getAlbums()));
        welcomeLabel.setText("Welcome, " + user.getUsername());
    }
}