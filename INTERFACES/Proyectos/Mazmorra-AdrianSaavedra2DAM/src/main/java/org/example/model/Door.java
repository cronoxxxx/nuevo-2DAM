package org.example.model;

public class Door {
    private String name;

    private String dest;

    public Door(String name, String dest) {
        this.name = name;
        this.dest = dest;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDest() {
        return this.dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }
}

