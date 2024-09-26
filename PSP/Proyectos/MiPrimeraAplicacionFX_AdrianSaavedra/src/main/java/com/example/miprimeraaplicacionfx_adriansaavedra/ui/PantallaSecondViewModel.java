package com.example.miprimeraaplicacionfx_adriansaavedra.ui;

import com.example.miprimeraaplicacionfx_adriansaavedra.Constantes;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Mensaje;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Usuario;
import com.example.miprimeraaplicacionfx_adriansaavedra.dao.Usuarios;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class TerceraPantallaMensajes implements Initializable {
    public TableView<Mensaje> tvTablaMensajes;
    public TableColumn<Mensaje, String> tcNombreUsuario;
    public TableColumn<Mensaje, String> tcMensaje;
    public TextArea taMensajeEnviado;
    public TextField tfNombreEnviar;
    public Button btnMandarMensaje;
    public MenuItem firstView;
    public MenuItem secondVIew;
    public MenuItem thirdView;
    public Menu MainManu;
    public AnchorPane anchorPane;
    private Usuarios usuarios;
    private SecondViewModel secondViewModel;
    public TerceraPantallaMensajes() {
        this.usuarios = new Usuarios();
        secondViewModel = new SecondViewModel(null);
    }



    public void comprobarUsuario(ActionEvent actionEvent) {
        if(!tfNombreEnviar.getText().isEmpty() && !taMensajeEnviado.getText().isEmpty()) {
            Usuario recibidor = usuarios.getUsuario(tfNombreEnviar.getText());

            if (recibidor != null) {
                Mensaje nuevoMensaje = new Mensaje(Constantes.getUSERNAME(), taMensajeEnviado.getText());
                recibidor.getMensajesRecibidos().add(nuevoMensaje);
                usuarios.saveUsuarios(usuarios.getUsuarioList());
                tfNombreEnviar.clear();
                taMensajeEnviado.clear();
            } else {
                showAlert("User not found", "The specified user does not exist.");
            }
        } else {
            showAlert("Empty fields", "Please fill in both the username and message fields.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Usuario usuario = null;
        MainManu.setVisible(true);
        usuario = usuarios.getUsuarioList().stream()
                .filter(u -> u.getNickname().equals(Constantes.getUSERNAME()))
                .findFirst()
                .orElse(null);

        tcNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcMensaje.setCellValueFactory(new PropertyValueFactory<>("texto"));

        if (usuario != null) {
            secondViewModel = new SecondViewModel(usuario);
            tvTablaMensajes.setItems(secondViewModel.getMensajesObservableList());

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
