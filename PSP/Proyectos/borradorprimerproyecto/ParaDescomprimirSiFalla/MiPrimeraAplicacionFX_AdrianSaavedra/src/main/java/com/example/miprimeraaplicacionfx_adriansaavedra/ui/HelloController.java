package com.example.miprimeraaplicacionfx_adriansaavedra.ui;


import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;
import com.example.miprimeraaplicacionfx_adriansaavedra.common.Constantes;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.service.GestionUsuarios;
import com.example.miprimeraaplicacionfx_adriansaavedra.ui.model.FXMLCargador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.Getter;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private MenuItem firstView;

    @FXML
    private TextField tfNewUser;
    @FXML
    private TextField tfPassword;
    @FXML@Getter
    private TextField tfUser;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnIniciar;
    @FXML
    private MenuBar mainMenu;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField tfnewPassword;
    private final GestionUsuarios gestionUsuarios;

    public HelloController() {
        this.gestionUsuarios = new GestionUsuarios();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainMenu.setVisible(true);
        mainMenu.setDisable(true);
    }


    public void registrarUser() {

        if (tfNewUser.getText().isEmpty() || tfnewPassword.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(Constantes.CAMPO_VACIO);
            alert.showAndWait();
        } else {
            Usuario registro = new Usuario(tfNewUser.getText(), tfnewPassword.getText());
            gestionUsuarios.obtenerUsuarios().add(registro);
            if (gestionUsuarios.saveUsuarios(gestionUsuarios.obtenerUsuarios())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Usuario registrado");
                alert.setHeaderText(null);
                alert.setContentText(Constantes.USUARIO_REGISTRADO_CORRECTAMENTE + tfNewUser.getText());
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al registrar");
                alert.setHeaderText(null);
                alert.setContentText(Constantes.ERROR_REGISTRO_USUARIO);
                alert.showAndWait();
            }
        }

    }

    public void comprobarUser() {
        Optional<Usuario> existe = gestionUsuarios.obtenerUsuarios().stream()
                .filter(usuario -> tfUser.getText().equals(usuario.getNickname())
                        && tfPassword.getText().equals(usuario.getPassword()))
                .findFirst();


        if (existe.isPresent()) {
            //activar meny
            Usuario usuario = existe.get();
            usuario.setIngreso(true);
            gestionUsuarios.actualizarUsuario(usuario);
            tfUser.setEditable(false);
            tfPassword.setEditable(false);
            tfnewPassword.setEditable(false);
            tfNewUser.setEditable(false);
            btnRegister.setDisable(true);
            btnIniciar.setDisable(true);
            mainMenu.setVisible(true);
            mainMenu.setDisable(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al registrar");
            alert.setHeaderText(null);
            alert.setContentText(Constantes.USUARIO_NO_EXISTE);
            alert.showAndWait();
        }
    }

    public void menuClick(ActionEvent actionEvent) {
        mainMenu.setVisible(true);
        Pane view;
        FXMLCargador fxmlCargador = new FXMLCargador();

        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "firstView" -> {
                view = fxmlCargador.getView("Screen1.fxml");
                borderPane.setCenter(view);
                mainMenu.setVisible(true);
            }
            case "secondView" -> {
                view = fxmlCargador.getView("Screen2.fxml");
                borderPane.setCenter(view);
                mainMenu.setVisible(true);
                firstView.setDisable(true);
            }
            case "thirdView" -> {
                view = fxmlCargador.getView("Screen3.fxml");
                borderPane.setCenter(view);
                mainMenu.setVisible(true);
                firstView.setDisable(true);
            }
            default -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(Constantes.ERROR_GENERAL);
                alert.showAndWait();
            }
        }
    }



}