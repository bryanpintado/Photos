package photos22;

import java.io.Serializable;

public class Album implements Serializable {
    private String name;

    public Album(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}