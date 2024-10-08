package com.example.pixelart;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage
        PixelArtScene scene = new PixelArtScene(new VBox(), 800, 800);

        stage.setScene(scene);
        stage.setTitle("Hello!");
        //stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}