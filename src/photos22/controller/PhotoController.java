package photos22.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import photos22.model.Photo;

public class PhotoController {
    @FXML
    private ImageView photoImageView;

    @FXML
    private Label captionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label tagsLabel;

    public void setPhoto(Photo photo) {
        photoImageView.setImage(new Image(photo.getFile().toURI().toString()));
        captionLabel.setText("Caption: " + photo.getCaption());
        dateLabel.setText("Date: " + photo.getDateString());
        tagsLabel.setText("Tags: " + photo.getTagsString());
    }
}