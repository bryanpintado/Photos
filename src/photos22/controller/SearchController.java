package photos22.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import photos22.model.*;
import photos22.util.AlertUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchController {

    @FXML
    private TextField tagField1;
    @FXML
    private TextField tagField2;
    @FXML
    private RadioButton singleRadio;
    @FXML
    private RadioButton andRadio;
    @FXML
    private RadioButton orRadio;
    @FXML
    private ListView<Photo> resultListView;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField endDateField;

    private User activeUser;

    public void setUser(User user) {
        this.activeUser = user;
    }

    @FXML
    private void handleSearch() {
        String input1 = tagField1.getText().trim();
        String input2 = tagField2.getText().trim();

        if (input1.isEmpty()) {
            AlertUtil.showAlert("At least one tag must be entered.");
            return;
        }

        Tag tag1 = parseTag(input1);
        Tag tag2 = input2.isEmpty() ? null : parseTag(input2);

        if (tag1 == null || (!input2.isEmpty() && tag2 == null)) {
            AlertUtil.showAlert("Invalid tag format. Use type=value.");
            return;
        }

        List<Photo> results = new ArrayList<>();
        for (Album album : activeUser.getAlbums()) {
            for (Photo photo : album.getPhotos()) {
                boolean match = (singleRadio.isSelected() && photo.getTags().contains(tag1)) ||
                        (andRadio.isSelected() && tag2 != null &&
                                photo.getTags().contains(tag1) && photo.getTags().contains(tag2))
                        ||
                        (orRadio.isSelected() && tag2 != null &&
                                (photo.getTags().contains(tag1) || photo.getTags().contains(tag2)));

                if (match && !results.contains(photo)) {
                    results.add(photo);
                }
            }
        }

        resultListView.setItems(FXCollections.observableArrayList(results));
    }

    private Tag parseTag(String input) {
        String[] parts = input.split("=");
        if (parts.length != 2)
            return null;
        return new Tag(parts[0].trim(), parts[1].trim());
    }

    @FXML
    private void handleCreateAlbum() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create Album");
        dialog.setHeaderText("Enter name for new album:");
        dialog.setContentText("Album Name:");

        String name = dialog.showAndWait().orElse(null);
        if (name == null || name.trim().isEmpty())
            return;

        Album newAlbum = new Album(name.trim());
        for (Photo photo : resultListView.getItems()) {
            newAlbum.addPhoto(photo);
        }

        activeUser.addAlbum(newAlbum);
        UserManager.getInstance().saveUsers();
        AlertUtil.showAlert("Album '" + name + "' created with search results.");
    }

    @FXML
private void handleBack() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/photos22/view/user_view.fxml"));
        Parent root = loader.load();

        UserController controller = loader.getController();
        controller.setUser(activeUser); // sets user back after returning

        Stage stage = (Stage) resultListView.getScene().getWindow();
        stage.setTitle("User Albums");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        AlertUtil.showAlert("Failed to load user view.\n" + e.getMessage());
    }
}

    @FXML
    private void handleSearchByDate() {
        String start = startDateField.getText().trim();
        String end = endDateField.getText().trim();

        if (start.isEmpty() || end.isEmpty()) {
            AlertUtil.showAlert("Both start and end dates are required.");
            return;
        }

        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            List<Photo> results = new ArrayList<>();
            for (Album album : activeUser.getAlbums()) {
                for (Photo photo : album.getPhotos()) {
                    LocalDate photoDate = photo.getModifiedDate().toLocalDate();
                    if (!results.contains(photo) && (photoDate.isEqual(startDate)
                            || photoDate.isEqual(endDate)
                            || (photoDate.isAfter(startDate) && photoDate.isBefore(endDate)))) {
                        results.add(photo);
                    }
                }
            }

            resultListView.setItems(FXCollections.observableArrayList(results));
        } catch (Exception e) {
            AlertUtil.showAlert("Invalid date format. Use yyyy-MM-dd");
        }
    }
}