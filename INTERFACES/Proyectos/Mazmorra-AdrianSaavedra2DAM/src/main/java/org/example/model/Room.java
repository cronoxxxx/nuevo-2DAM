package org.example.model;

import java.util.List;

public class Room {
    private String id;

    private List<Door> doors;

    private String description;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Door> getDoors() {
        return this.doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
