package com.example.miprimeraaplicacionfx_adriansaavedra.ui.model;

import com.example.miprimeraaplicacionfx_adriansaavedra.ui.HelloApplication;
import javafx.scene.layout.Pane;


import java.net.URL;
import javafx.fxml.FXMLLoader;

public class FXMLCargador {

    public Pane getView(String fxml) {
        try {
            URL fileUrl = HelloApplication.class.getResource("/com/example/miprimeraaplicacionfx_adriansaavedra/" + fxml);
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            return FXMLLoader.load(fileUrl);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


}
