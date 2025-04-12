package photos22.model;

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    public ObservableList<Album> albums = FXCollections.observableArrayList();

    private String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

    public String getUsername() {
        return username;
    }
}