package photos22.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Album> albums = new ArrayList<>();

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
    public List<Album> getAlbums() {
        return albums;
    }
}