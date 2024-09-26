package com.example.miprimeraaplicacionfx_adriansaavedra.ui;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Usersession;
import javafx.scene.layout.Pane;


import java.net.URL;

public class FXMLLoader {

    private Usersession usersession;
    public FXMLLoader(Usersession userSession) {
        this.usersession = userSession;
    }
    public Pane getView(String fxml) {
        try {
            URL fileUrl = HelloApplication.class.getResource("/com/example/miprimeraaplicacionfx_adriansaavedra/" + fxml);
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            return new javafx.fxml.FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object createController(Class<?> type) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        if (type == HelloController.class) {
            return new HelloController(userSession);
        } else if (type == PantallaMainViewModel.class) {
            return new PantallaMainViewModel(userSession);
        } else if (type == PantallaSecondViewModel.class) {
            return new PantallaSecondViewModel(userSession);
        }
        // Añade más controladores según sea necesario
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
