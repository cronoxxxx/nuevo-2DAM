package com.example.miprimeraaplicacionfx_adriansaavedra.ui;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.service.GestionUsuarios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/miprimeraaplicacionfx_adriansaavedra/Screen1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Hello!");
        stage.setOnCloseRequest( //Se encarga de cambiar el estado de los usuarios a offline al cerrar la aplicación
                e->{
                    GestionUsuarios gestionUsuarios = new GestionUsuarios();
                    gestionUsuarios.obtenerUsuarios().forEach(usuario -> {
                        usuario.setIngreso(false);
                        gestionUsuarios.actualizarUsuario(usuario);
                    });
                    stage.close();
                }
        );
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
