package com.example.miprimeraaplicacionfx_adriansaavedra.screens;

import com.example.miprimeraaplicacionfx_adriansaavedra.Constantes;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Usuario;
import com.example.miprimeraaplicacionfx_adriansaavedra.dao.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
@Getter@Setter
public class HelloController implements Initializable {
    public TextField tfNewUser;
    public TextField tfPassword;
    @FXML
    public TextField tfUser;
    public Button btnRegister;
    public Button btnIniciar;
    public MenuItem firstView;
    public MenuItem secondVIew;
    public MenuItem thirdView;
    public MenuBar MainManu;
    public AnchorPane anchorPane;
    @FXML
    private TextField tfnewPassword;
    private Usuarios usuarios;

    public HelloController() {
        this.usuarios = new Usuarios();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
MainManu.setVisible(true);
MainManu.setDisable(true);
    }






    public void registrarUser() {

        if (tfNewUser.getText().isEmpty() || tfnewPassword.getText().isEmpty()) {
            //poner label confirmando que no se guardaron los usuarios
        } else {
            Usuario usuario = new Usuario(tfNewUser.getText(), tfnewPassword.getText());
            usuarios.getUsuarioList().add(usuario);
            if(usuarios.saveUsuarios(usuarios.getUsuarioList())) {
                //poner label confirmando que se guardaron los usuarios
            } else {
                //poner label confirmando que no se guardaron los usuarios
            }
        }

    }

    public void comprobarUser() {
        Optional<Usuario> existe = usuarios.getUsuarioList().stream()
                .filter(usuario -> tfUser.getText().equals(usuario.getNickname())
                        && tfPassword.getText().equals(usuario.getPassword()))
                .findFirst();

        Constantes.setUSERNAME(tfUser.getText());


        if(existe.isPresent()) {
            //activar meny
            tfUser.setEditable(false);
            tfPassword.setEditable(false);
            tfnewPassword.setEditable(false);
            tfNewUser.setEditable(false);
            btnRegister.setDisable(true);
            btnIniciar.setDisable(true);
            MainManu.setVisible(true);
            MainManu.setDisable(false);
        } else {
            //poner label confirmando que el usuario no existe
        }
    }

    public void menuClick(ActionEvent actionEvent) {
        MainManu.setVisible(true);
        Pane view;
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "firstView":
                FXMLLoader loader = new FXMLLoader();
                view = loader.getView("Screen1.fxml");
                if (view != null) {
                    anchorPane.getChildren().setAll(view);

                }
                break;
            case "secondVIew":
                loader = new FXMLLoader();
                view = loader.getView("Screen2.fxml");
                if (view != null) {
                    anchorPane.getChildren().setAll(view);

                }
                break;
            case "thirdView":
                loader = new FXMLLoader();
                view = loader.getView("Screen3.fxml");
                if (view != null) {
                    anchorPane.getChildren().setAll(view);

                }
                break;
            default:
                break;
        }
    }




}