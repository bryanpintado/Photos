package photos22.controller;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import photos22.model.Album;
import photos22.model.Photo;
import photos22.model.UserManager;
import photos22.util.AlertUtil;
import photos22.model.Tag;

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

    @FXML
    private void handleAddPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile == null) {
            return;
        }

        String filePath = selectedFile.getAbsolutePath();
        for (Photo p : album.getPhotos()) {
            if (p.getFile().getAbsolutePath().equals(filePath)) {
                AlertUtil.showAlert("This photo is already in the album.");
                return;
            }
        }

        Photo photo = new Photo(selectedFile);
        album.getPhotos().add(photo);
        UserManager.getInstance().saveUsers();
        photoListView.setItems(FXCollections.observableArrayList(album.getPhotos()));
    }

    @FXML
    private void handleRemovePhoto() {
        Photo selected = photoListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showAlert("Please select a photo to remove.");
            return;
        }

        album.getPhotos().remove(selected);
        UserManager.getInstance().saveUsers();
        photoListView.setItems(FXCollections.observableArrayList(album.getPhotos()));
    }

    @FXML
    private void handleViewPhoto() {
        Photo selected = photoListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showAlert("Please select a photo to view.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/photos22/view/photo_view.fxml"));
            Parent root = loader.load();

            PhotoController controller = loader.getController();
            controller.setPhoto(selected);

            Stage stage = new Stage();
            stage.setTitle("Photo Viewer");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUtil.showAlert("Failed to load photo view.\n" + e.toString());
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/photos22/view/user_view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) photoListView.getScene().getWindow();
            stage.setTitle("User Albums");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            AlertUtil.showAlert("Failed to return to album list.\n" + e.toString());
        }
    }

    @FXML
    private void handleAddTag() {
        Photo selected = photoListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showAlert("Please select a photo to tag.");
            return;
        }

        TextInputDialog typeDialog = new TextInputDialog();
        typeDialog.setTitle("Add Tag");
        typeDialog.setHeaderText("Enter Tag Type (e.g., person, location)");
        typeDialog.setContentText("Type:");
        String type = typeDialog.showAndWait().orElse(null);
        if (type == null || type.trim().isEmpty())
            return;

        TextInputDialog valueDialog = new TextInputDialog();
        valueDialog.setTitle("Add Tag");
        valueDialog.setHeaderText("Enter Tag Value");
        valueDialog.setContentText("Value:");
        String value = valueDialog.showAndWait().orElse(null);
        if (value == null || value.trim().isEmpty())
            return;

        Tag newTag = new Tag(type.trim(), value.trim());
        if (!selected.addTag(newTag)) {
            AlertUtil.showAlert("Tag already exists for this photo.");
            return;
        }

        UserManager.getInstance().saveUsers();
        // AlertUtil.showAlert("Tag added successfully.");
    }

    @FXML
    private void handleRemoveTag() {
        Photo selected = photoListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showAlert("Please select a photo to remove a tag from.");
            return;
        }

        TextInputDialog typeDialog = new TextInputDialog();
        typeDialog.setTitle("Remove Tag");
        typeDialog.setHeaderText("Enter Tag Type to Remove");
        typeDialog.setContentText("Type:");
        String type = typeDialog.showAndWait().orElse(null);
        if (type == null || type.trim().isEmpty())
            return;

        TextInputDialog valueDialog = new TextInputDialog();
        valueDialog.setTitle("Remove Tag");
        valueDialog.setHeaderText("Enter Tag Value to Remove");
        valueDialog.setContentText("Value:");
        String value = valueDialog.showAndWait().orElse(null);
        if (value == null || value.trim().isEmpty())
            return;

        Tag tagToRemove = new Tag(type.trim(), value.trim());
        if (!selected.removeTag(tagToRemove)) {
            AlertUtil.showAlert("This tag was not found on the selected photo.");
            return;
        }

        UserManager.getInstance().saveUsers();
        AlertUtil.showAlert("Tag removed successfully.");
    }
}