package com.example.miprimeraaplicacionfx_adriansaavedra.ui;


import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Mensaje;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;
import com.example.miprimeraaplicacionfx_adriansaavedra.common.Constantes;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.service.GestionGrupos;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.service.GestionUsuarios;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.Getter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class BuzonController implements Initializable {

    @FXML
    private ListView<String> lvGruposDeUsuario;
    @FXML
    private ListView<Mensaje> lvMensajesGrupo;
    @FXML
    private TextField tfNewGroup;
    @FXML
    private TextField tfnewPasswordGroup;
    @FXML
    private Button btnRegisterPublicGroup;
    @FXML
    private Button btnRegisterSecretGroup;
    @FXML
    private TextField tfGroup;
    @FXML
    private Button btnIniciarGroup;
    @FXML
    private TextField tfPasswordGroup;
    @FXML
    private ListView<String> lvGrupoParticipantes;
    @FXML
    private TextArea taContenidoEnviar;
    @FXML
    private ListView<Mensaje> lvMensajesRecibidosUser;
    @FXML
    private ListView<String> lvGruposSecretos;
    @FXML
    private TextField tfAgregarUsuarioGrupo;
    @FXML
    private TextField tfNewUser;
    @FXML
    private TextField tfPasswordUser;
    @FXML
    @Getter
    private TextField tfUser;
    @FXML
    private Button btnRegisterUser;
    @FXML
    private Button btnIniciarUser;

    @FXML
    private TextField tfnewPasswordUser;
    private final GestionUsuarios gestionUsuarios;
    private final GestionGrupos gestionGrupos;
    private Usuario usuario;
    private Grupo grupoActual;

    public BuzonController() {
        this.gestionUsuarios = new GestionUsuarios();
        this.gestionGrupos = new GestionGrupos();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gestionUsuarios.obtenerUsuarios().stream().filter(Usuario::isIngreso)
                .forEach(user -> user.setIngreso(false));
        lvMensajesRecibidosUser.setItems(FXCollections.observableArrayList());
        lvGrupoParticipantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvGruposSecretos.setItems(FXCollections.observableArrayList());
        lvMensajesGrupo.setItems(FXCollections.observableArrayList());
        lvGruposDeUsuario.setItems(FXCollections.observableArrayList());

        setupGroupListeners();

    }

    private void setupGroupListeners() {
        lvGruposDeUsuario.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarGrupo(newValue);
                lvGruposSecretos.getSelectionModel().clearSelection();
            }
        });

        lvGruposSecretos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                cargarGrupo(newValue);
                lvGruposDeUsuario.getSelectionModel().clearSelection();
            }
        });
    }

    private void cargarGrupo(String nombreGrupo) {
        Grupo grupoSeleccionado = gestionGrupos.obtenerGrupos().stream()
                .filter(g -> g.getNombre().equals(nombreGrupo))
                .findFirst()
                .orElse(null);

        if (grupoSeleccionado != null) {
            grupoActual = grupoSeleccionado;
            actualizarVistaGrupo();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_GRUPO_NO_ENCONTRADO, Constantes.CONTENIDO_ERROR_GRUPO_NO_ENCONTRADO);
        }
    }


    public void registrarUser() { //done

        if (tfNewUser.getText().isEmpty() || tfnewPasswordUser.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(Constantes.CAMPO_VACIO);
            alert.showAndWait();
        } else {
            Usuario registro = new Usuario(tfNewUser.getText(), tfnewPasswordUser.getText());
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

    public void comprobarUser() { //done
        Optional<Usuario> existe = gestionUsuarios.obtenerUsuarios().stream()
                .filter(usuario -> tfUser.getText().equals(usuario.getNombre())
                        && tfPasswordUser.getText().equals(usuario.getPassword()))
                .findFirst();


        if (existe.isPresent()) {
            //activar meny
            usuario = existe.get();
            usuario.setIngreso(true);
            lvMensajesRecibidosUser.getItems().addAll(usuario.getMensajesRecibidos());
            lvGruposSecretos.getItems().addAll(usuario.getGruposSecretos());//funciona
            lvGruposDeUsuario.getItems().addAll(usuario.getGruposPublicos());
            gestionUsuarios.actualizarUsuario(usuario);
            tfUser.setEditable(false);
            tfPasswordUser.setEditable(false);
            tfnewPasswordUser.setEditable(false);
            tfNewUser.setEditable(false);
            btnRegisterUser.setDisable(true);
            btnIniciarUser.setDisable(true);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al registrar");
            alert.setHeaderText(null);
            alert.setContentText(Constantes.USUARIO_NO_EXISTE);
            alert.showAndWait();
        }
    }

    public void accederGrupoPublico() {
        Grupo grupo;

        if (tfGroup.getText().isEmpty() || tfPasswordGroup.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(Constantes.CAMPO_VACIO);
            alert.showAndWait();
        } else {
            grupo = gestionGrupos.obtenerGrupos().stream()
                    .filter(grupo1 -> tfGroup.getText().equals(grupo1.getNombre())
                            && tfPasswordGroup.getText().equals(grupo1.getPassword()))
                    .findFirst().orElse(null);
            if (grupo != null) {
                //activar meny
                if (!grupo.isEsPublico()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(Constantes.GRUPO_NO_ES_PUBLICO);
                    alert.showAndWait();
                } else {
                    tfGroup.setEditable(false);
                    tfPasswordGroup.setEditable(false);
                    btnRegisterSecretGroup.setDisable(true);
                    btnRegisterPublicGroup.setDisable(true);
                    btnIniciarGroup.setDisable(true);
                    grupoActual = grupo;
                    grupoActual.agregarMiembro(usuario.getNombre());
                    usuario.agregarGrupoPublico(grupoActual.getNombre());
                    lvGruposDeUsuario.getItems().addAll(usuario.getGruposPublicos());
                    gestionGrupos.actualizarGrupos(grupoActual);
                    gestionUsuarios.actualizarUsuario(usuario);
                    actualizarVistaGrupo();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(Constantes.GRUPO_NO_EXISTE);
                alert.showAndWait();
            }

        }
    }

    private void actualizarVistaGrupo() {
        tfGroup.setEditable(false);
        tfPasswordGroup.setEditable(false);
        btnRegisterSecretGroup.setDisable(true);
        btnRegisterPublicGroup.setDisable(true);
        btnIniciarGroup.setDisable(true);
        lvMensajesGrupo.getItems().clear();
        lvMensajesGrupo.getItems().addAll(grupoActual.getMensajes());
        lvGrupoParticipantes.getItems().clear();
        lvGrupoParticipantes.getItems().addAll(grupoActual.getParticipantes());
        tfGroup.setText(grupoActual.getNombre());
        tfPasswordGroup.setText(grupoActual.getPassword());
    }

    public void registrarPublicGroup() {
        if (!tfNewGroup.getText().isEmpty() && !tfnewPasswordGroup.getText().isEmpty()) {
            Grupo nuevo = new Grupo(tfNewGroup.getText(), tfnewPasswordGroup.getText(), true);
            gestionGrupos.obtenerGrupos().add(nuevo);
            usuario.agregarGrupoPublico(nuevo.getNombre());
            lvGruposDeUsuario.getItems().add(nuevo.getNombre());
            gestionGrupos.saveGrupos(gestionGrupos.obtenerGrupos());
            gestionUsuarios.saveUsuarios(gestionUsuarios.obtenerUsuarios());
            tfNewGroup.clear();
            tfnewPasswordGroup.clear();
        }
    }

    public void registrarSecretGroup() {
        if (!tfNewGroup.getText().isEmpty() && !tfnewPasswordGroup.getText().isEmpty()) {
            Grupo nuevo = new Grupo(tfNewGroup.getText(), tfnewPasswordGroup.getText(), false);
            gestionGrupos.obtenerGrupos().add(nuevo);
            //hacer que aparezca en la list view del usuario de grupos secretos
            usuario.agregarGrupoSecreto(nuevo.getNombre());
            lvGruposSecretos.getItems().add(nuevo.getNombre());
            gestionUsuarios.saveUsuarios(gestionUsuarios.obtenerUsuarios());
            gestionGrupos.saveGrupos(gestionGrupos.obtenerGrupos());
            tfNewGroup.clear();
            tfnewPasswordGroup.clear();
        }

    }

    public void enviarMensajeGrupos() {
        if (grupoActual != null && !taContenidoEnviar.getText().isEmpty()) {
            Mensaje mensaje = new Mensaje(usuario.getNombre(), taContenidoEnviar.getText());
            grupoActual.agregarMensaje(mensaje);
            gestionGrupos.actualizarGrupos(grupoActual);
            lvMensajesGrupo.getItems().add(mensaje);
            //lvMensajesGrupo.scrollTo(lvMensajesGrupo.getItems().size() - 1);
            taContenidoEnviar.clear();
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_ENVIAR_MENSAJE_VACIO, Constantes.CONTENIDO_ERROR_ENVIAR_MENSAJE_VACIO);
        }
    }

    public void agregarUsuarioGrupo() {
        String nombreUsuario = tfAgregarUsuarioGrupo.getText();
        if (!nombreUsuario.isEmpty() && grupoActual != null && !grupoActual.isEsPublico()) {
            Usuario usuarioAgregar = gestionUsuarios.getUsuario(nombreUsuario);
            if (usuarioAgregar != null) {
                grupoActual.agregarMiembro(usuarioAgregar.getNombre());
                usuarioAgregar.agregarGrupoSecreto(grupoActual.getNombre());
                gestionGrupos.actualizarGrupos(grupoActual);
                gestionUsuarios.actualizarUsuario(usuarioAgregar);
                actualizarVistaGrupo();
                mostrarAlerta(Alert.AlertType.INFORMATION, Constantes.TITULO_USUARIO_AGREGADO, Constantes.CONTENIDO_USUARIO_AGREGADO);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_USUARIO_NO_ENCONTRADO, Constantes.CONTENIDO_ERROR_USUARIO_NO_ENCONTRADO);
            }
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_CAMPO_VACIO, Constantes.CONTENIDO_ERROR_CAMPO_VACIO);
        }
    }

    public void enviarMensajePersonas() {
        List<String> destinatarios = new ArrayList<>(lvGrupoParticipantes.getSelectionModel().getSelectedItems());
        if (!destinatarios.isEmpty() && !taContenidoEnviar.getText().isEmpty()) {
            Mensaje mensaje = new Mensaje(usuario.getNombre(), taContenidoEnviar.getText());
            for (String destinatario : destinatarios) {
                Usuario usuarioDestino = gestionUsuarios.getUsuario(destinatario);
                if (usuarioDestino != null) {
                    usuarioDestino.getMensajesRecibidos().add(mensaje);
                    gestionUsuarios.actualizarUsuario(usuarioDestino);
                }
            }
            taContenidoEnviar.clear();
            mostrarAlerta(Alert.AlertType.INFORMATION, Constantes.TITULO_MENSAJE_ENVIADO, Constantes.CONTENIDO_MENSAJE_ENVIADO);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_SELECCION_DESTINATARIO, Constantes.CONTENIDO_ERROR_SELECCION_DESTINATARIO);
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}