package org.example.impl.moves;

import org.example.model.Room;

import java.util.EventListener;

public interface MazmorraMoveListener extends EventListener {
    void roomUpdated(Room paramRoom);
}
