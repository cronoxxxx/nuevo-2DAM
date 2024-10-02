package com.example.miprimeraaplicacionfx_adriansaavedra.ui;


import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Mensaje;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.service.GestionUsuarios;
import com.example.miprimeraaplicacionfx_adriansaavedra.ui.model.SecondViewModel;
import com.example.miprimeraaplicacionfx_adriansaavedra.common.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import lombok.Data;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
@Data
public class PantallaSecondViewModel implements Initializable {

    @FXML
    private Label content;
    @FXML
    private TableView<Mensaje> tvTablaMensajes;
    @FXML
    private TableColumn<Mensaje, String> tcNombreUsuario;
    @FXML
    private TableColumn<Mensaje, String> tcMensaje;
    @FXML
    private TextArea taMensajeEnviado;
    @FXML
    private TextField tfNombreEnviar;

    private final GestionUsuarios usuarios;
    private SecondViewModel secondViewModel;
    private Usuario usuario;

    public PantallaSecondViewModel() {
        this.usuarios = new GestionUsuarios();
        secondViewModel = new SecondViewModel(null);
    }



    public void comprobarUsuario() {
        if(!tfNombreEnviar.getText().isEmpty() && !taMensajeEnviado.getText().isEmpty()) {
            Usuario recibidor = usuarios.getUsuario(tfNombreEnviar.getText());

            if (recibidor != null) {
                Mensaje nuevoMensaje = new Mensaje(usuario.getNickname(), taMensajeEnviado.getText());
                recibidor.getMensajesRecibidos().add(nuevoMensaje);
                usuarios.saveUsuarios(usuarios.obtenerUsuarios());
                tfNombreEnviar.clear();
                taMensajeEnviado.clear();
            } else {
                showAlert(Constantes.TITULO_ERROR_USUARIO_NO_ENCONTRADO, Constantes.CONTENIDO_ERROR_USUARIO_NO_ENCONTRADO);
            }
        } else {
            showAlert(Constantes.TITULO_ERROR_CAMPO_VACIO, Constantes.CONTENIDO_ERROR_CAMPO_VACIO);
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
        Optional<Usuario> optionalUsuario = usuarios.obtenerUsuarios().stream()
                .filter(Usuario::isIngreso)
                .findFirst();

        tcNombreUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcMensaje.setCellValueFactory(new PropertyValueFactory<>("texto"));

        if (optionalUsuario.isPresent()) {
            usuario = optionalUsuario.get();
            secondViewModel = new SecondViewModel(usuario);
            tvTablaMensajes.setItems(secondViewModel.getMensajesObservableList());
        }

        tvTablaMensajes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Actualizar el contenido del label con el mensaje seleccionado
                content.setText(newSelection.getTexto());
            } else {
                // Si no hay selección, limpiar el label
                content.setText("");
            }
        });


    }



}
