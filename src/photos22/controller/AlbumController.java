package photos22.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import photos22.model.Album;
import photos22.model.Photo;

public class AlbumController {
    private Album album;

    @FXML
    private Label albumNameLabel;

    @FXML
    private ListView<Photo> photoListView;

    public void setAlbum(Album album) {
        this.album = album;
        albumNameLabel.setText(album.getName());
        photoListView.setItems(FXCollections.observableArrayList(album.getPhotos()));
    }

    // TODO: handleRemovePhoto, handleViewPhoto, handleBack
}