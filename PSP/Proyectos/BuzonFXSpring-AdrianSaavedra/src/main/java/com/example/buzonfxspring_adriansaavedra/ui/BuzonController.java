package com.example.buzonfxspring_adriansaavedra.ui;


import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorApp;
import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorAppDataBase;
import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorAppDatosNoValidos;
import com.example.buzonfxspring_adriansaavedra.domain.model.*;
import com.example.buzonfxspring_adriansaavedra.common.Constantes;
import com.example.buzonfxspring_adriansaavedra.domain.service.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

@Component
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
    private TextField tfUser;
    @FXML
    private Button btnRegisterUser;
    @FXML
    private Button btnIniciarUser;

    @FXML
    private TextField tfnewPasswordUser;
    private final GestionUsuarios gestionUsuarios;
    private final GestionGrupos gestionGrupos;
    private final GestionMensajes gestionMensajes;
    private Usuario usuario;
    private Grupo grupoActual;

    public BuzonController(GestionUsuarios gestionUsuarios, GestionGrupos gestionGrupos, GestionMensajes gestionMensajes) {

        this.gestionUsuarios = gestionUsuarios;
        this.gestionGrupos = gestionGrupos;
        this.gestionMensajes = gestionMensajes;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Arrays.asList(lvMensajesRecibidosUser, lvGruposSecretos, lvMensajesGrupo, lvGruposDeUsuario)
                .forEach(lv -> lv.setItems(FXCollections.observableArrayList()));
        lvGrupoParticipantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
        gestionGrupos.obtenerGrupoPorNombre(nombreGrupo)
                .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_GRUPO_NO_ENCONTRADO, error))
                .peek(grupoSeleccionado -> {
                    grupoActual = grupoSeleccionado;
                    actualizarVistaGrupo();
                });
    }





    public void registrarUser() {
        if (tfNewUser.getText().isEmpty() || tfnewPasswordUser.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_LOGIN, Constantes.CONTENIDO_ERROR_LOGIN);
        } else {
            Usuario registro = new Usuario(tfNewUser.getText(), tfnewPasswordUser.getText());
            gestionUsuarios.addUsuario(registro)
                    .peekLeft(this::mostrarError)
                    .peek(success -> mostrarAlerta(Alert.AlertType.CONFIRMATION, Constantes.TITULO_USUARIO_REGISTRADO,
                            Constantes.CONTENIDO_USUARIO_REGISTRADO_CORRECTAMENTE + tfNewUser.getText()));
        }
    }

    public void comprobarUser() {
        if (tfUser.getText().isEmpty() || tfPasswordUser.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_LOGIN, Constantes.CONTENIDO_ERROR_LOGIN);
        } else {
            Usuario x = new Usuario(tfUser.getText(), tfPasswordUser.getText());
            gestionUsuarios.verificacion(x)
                    .peekLeft(this::mostrarError)
                    .peek(usuarioVerificado -> {
                        usuario = usuarioVerificado;
                        cargarMensajesUsuario();
                        cargarGruposUsuario();
                        actualizarInterfazPostLogin();
                        mostrarAlerta(Alert.AlertType.INFORMATION, Constantes.TITULO_EXITO_INICIO_SESION,
                                Constantes.CONTENIDO_EXITO_INICIO_SESION + usuario.getNombre());
                    });
        }
    }

    private void cargarMensajesUsuario() {
        gestionMensajes.obtenerMensajesParaUsuario(usuario)
                .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_OBTENER_MENSAJES, error))
                .peek(mensajes -> lvMensajesRecibidosUser.getItems().addAll(mensajes));
    }

    private void cargarGruposUsuario() {
        cargarGrupos(usuario.getNombre(), true, lvGruposDeUsuario);
        cargarGrupos(usuario.getNombre(), false, lvGruposSecretos);
    }

    private void actualizarInterfazPostLogin() {
        tfUser.setEditable(false);
        tfPasswordUser.setEditable(false);
        tfnewPasswordUser.setEditable(false);
        tfNewUser.setEditable(false);
        btnRegisterUser.setDisable(true);
        btnIniciarUser.setDisable(true);
    }

    private void cargarGrupos(String nombreUsuario, boolean esPublico, ListView<String> listView) {
        gestionGrupos.obtenerGruposParaUsuario(nombreUsuario, esPublico)
                .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_OBTENER_GRUPOS, error))
                .peek(grupos -> listView.getItems().addAll(grupos));
    }

    public void accederGrupoPublico() {
        if (sonCamposValidos()) {
            Grupo x = new Grupo(tfGroup.getText(), tfPasswordGroup.getText(), usuario, true);
            gestionGrupos.ingresar(x)
                    .peekLeft(this::manejarErrorIngreso)
                    .peek(this::procesarIngresoExitoso);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_CAMPO_VACIO, Constantes.CONTENIDO_ERROR_CAMPO_VACIO);
        }
    }

    private boolean sonCamposValidos() {
        return tfGroup.getText() != null && !tfGroup.getText().isEmpty() &&
                tfPasswordGroup.getText() != null && !tfPasswordGroup.getText().isEmpty();
    }

    private void manejarErrorIngreso(String error) {
        mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_GRUPO_NO_ENCONTRADO, error);
    }

    private void procesarIngresoExitoso(Grupo comprobar) {
        grupoActual = comprobar;
        deshabilitarCamposYBotones();
        if (usuario != null && !usuario.equals(comprobar.getAdministrador())) {
            agregarUsuarioAlGrupo(comprobar);
        } else if (usuario != null) {
            mostrarAlerta(Alert.AlertType.INFORMATION, Constantes.TITULO_DISFRUTE, Constantes.DISFRUTE);
        }
        actualizarGrupoYVistas(comprobar);
    }

    private void deshabilitarCamposYBotones() {
        tfGroup.setEditable(false);
        tfPasswordGroup.setEditable(false);
        btnRegisterSecretGroup.setDisable(true);
        btnRegisterPublicGroup.setDisable(true);
        btnIniciarGroup.setDisable(true);
    }

    private void agregarUsuarioAlGrupo(Grupo grupo) {
        gestionGrupos.agregarMiembroGrupo(grupo, usuario)
                .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_AGREGAR_MIEMBRO, error))
                .peek(agregado -> {
                    if (agregado != null && agregado) {
                        mostrarAlerta(Alert.AlertType.INFORMATION, Constantes.TITULO_USUARIO_AGREGADO, Constantes.CONTENIDO_USUARIO_AGREGADO);
                    }
                });
    }

    private void actualizarGrupoYVistas(Grupo grupo) {
        gestionGrupos.actualizarGrupo(grupo);
        actualizarListaParticipantes(grupo);
        actualizarListaGrupos(grupo);
        actualizarListaMensajes(grupo);
        actualizarVistaGrupo();
    }

    public void registrarPublicGroup() {
        if (tfNewGroup.getText().isEmpty() || tfnewPasswordGroup.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_GRUPO_NO_ENCONTRADO, Constantes.CONTENIDO_ERROR_GRUPO_NO_ENCONTRADO);
        } else {
            Grupo grupo = new Grupo(tfNewGroup.getText(), tfnewPasswordGroup.getText(), usuario, true);
            gestionGrupos.addGroup(grupo)
                    .flatMap(added -> gestionGrupos.obtenerGrupos().flatMap(gestionGrupos::saveGrupos))
                    .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_REGISTRO_GRUPO, error))
                    .peek(success -> {
                        mostrarAlerta(Alert.AlertType.CONFIRMATION, Constantes.TITULO_EXITO_GRUPO_REGISTRADO, Constantes.CONTENIDO_EXITO_GRUPO_REGISTRADO);
                        lvGruposDeUsuario.getItems().add(grupo.getNombre());
                    });
        }
    }

    public void registrarSecretGroup() {
        if (tfNewGroup.getText().isEmpty() || tfnewPasswordGroup.getText().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_GRUPO_NO_ENCONTRADO, Constantes.CONTENIDO_ERROR_GRUPO_NO_ENCONTRADO);
        } else {
            Grupo grupo = new Grupo(tfNewGroup.getText(), tfnewPasswordGroup.getText(), usuario, false);
            gestionGrupos.addGroup(grupo)
                    .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_REGISTRO_GRUPO, error))
                    .peek(success -> {
                        if (success != null && success) {
                            mostrarAlerta(Alert.AlertType.CONFIRMATION, Constantes.TITULO_EXITO_GRUPO_REGISTRADO, Constantes.CONTENIDO_EXITO_GRUPO_REGISTRADO);
                            lvGruposSecretos.getItems().add(grupo.getNombre());
                        } else {
                            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_REGISTRO_GRUPO, Constantes.CONTENIDO_ERROR_REGISTRO_GRUPO);
                        }
                    });
        }
    }

    public void enviarMensajeGrupos() {
        if (grupoActual != null && !taContenidoEnviar.getText().isEmpty()) {
            Mensaje mensaje = new Mensaje(taContenidoEnviar.getText(), LocalDateTime.now(), usuario, Collections.emptyList(), grupoActual.getNombre());
            gestionMensajes.addMensajes(mensaje)
                    .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_ENVIO_MENSAJE, error))
                    .peek(success -> {
                        lvMensajesGrupo.getItems().add(mensaje);
                        lvMensajesGrupo.scrollTo(lvMensajesGrupo.getItems().size() - 1);
                        taContenidoEnviar.clear();
                    });
        }
    }

    public void agregarUsuarioAGrupo() {
        if (sonDatosValidos()) {
            if (esAdministradorDelGrupo()) {
                gestionUsuarios.buscarUsuarioPorNombre(tfAgregarUsuarioGrupo.getText())
                        .peekLeft(this::mostrarError)
                        .peek(this::agregarMiembroSiNoExiste);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_PERMISO_DENEGADO, Constantes.CONTENIDO_PERMISO_DENEGADO);
            }
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_CAMPO_VACIO, Constantes.CONTENIDO_ERROR_CAMPO_VACIO);
        }
    }

    public void enviarMensajePersonas() {
        List<String> destinatarios = new ArrayList<>(lvGrupoParticipantes.getSelectionModel().getSelectedItems());
        if (!taContenidoEnviar.getText().isEmpty() && !destinatarios.isEmpty()) {
            gestionUsuarios.buscarUsuariosPorNombres(destinatarios)
                    .peekLeft(this::mostrarError)
                    .peek(existentes -> {
                        if (!existentes.isEmpty()) {
                            Mensaje mensaje = new Mensaje(taContenidoEnviar.getText(), LocalDateTime.now(), usuario, existentes, null);
                            gestionMensajes.addMensajes(mensaje)
                                    .peekLeft(errorMensaje -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_ENVIO_MENSAJE, errorMensaje))
                                    .peek(mensajeGuardado -> {
                                        mostrarAlerta(Alert.AlertType.CONFIRMATION, Constantes.TITULO_EXITO_ENVIO_MENSAJE, Constantes.CONTENIDO_EXITO_ENVIO_MENSAJE);
                                        taContenidoEnviar.clear();
                                        if (existentes.stream().anyMatch(u -> u.equals(usuario))) {
                                            actualizarListaMensajesUsuario();
                                        }
                                    });
                        }
                    });
        }
    }

    private void actualizarListaMensajesUsuario() {
        gestionMensajes.obtenerMensajesParaUsuario(usuario)
                .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_OBTENER_MENSAJES, error))
                .peek(mensajesUsuario -> {
                    lvMensajesRecibidosUser.getItems().clear();
                    lvMensajesRecibidosUser.getItems().addAll(mensajesUsuario);
                });
    }


    private void actualizarListaParticipantes(Grupo grupo) {
        lvGrupoParticipantes.getItems().clear();
        if (grupo.getParticipantes() != null) {
            grupo.getParticipantes().stream()
                    .map(Usuario::getNombre)
                    .filter(Objects::nonNull)
                    .forEach(lvGrupoParticipantes.getItems()::add);
        }
        if (grupo.getAdministrador() != null && grupo.getAdministrador().getNombre() != null) {
            lvGrupoParticipantes.getItems().add(grupo.getAdministrador().getNombre());
        }
        if (usuario != null && !lvGrupoParticipantes.getItems().contains(usuario.getNombre())) {
            lvGrupoParticipantes.getItems().add(usuario.getNombre());
        }
    }

    private void actualizarListaGrupos(Grupo grupo) {
        if (!lvGruposDeUsuario.getItems().contains(grupo.getNombre())) {
            lvGruposDeUsuario.getItems().add(grupo.getNombre());
        }
        lvGruposDeUsuario.getSelectionModel().select(grupo.getNombre());
    }

    private void actualizarListaMensajes(Grupo grupo) {
        lvMensajesGrupo.getItems().clear();
        gestionMensajes.obtenerMensajes()
                .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_OBTENER_MENSAJES, error))
                .peek(mensajes -> {
                    if (mensajes != null) {
                        gestionMensajes.obtenerMensajesDeGrupo(grupo)
                                .peekLeft(errorGrupo -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_OBTENER_MENSAJES_GRUPO, errorGrupo))
                                .peek(mensajesGrupo -> mensajesGrupo.forEach(mensaje -> lvMensajesGrupo.getItems().add(mensaje)));
                    }
                });
    }

    private void actualizarVistaGrupo() {
        tfGroup.setEditable(false);
        tfPasswordGroup.setEditable(false);
        btnRegisterSecretGroup.setDisable(true);
        btnRegisterPublicGroup.setDisable(true);
        btnIniciarGroup.setDisable(true);
        tfGroup.setText(grupoActual.getNombre());
        tfPasswordGroup.setText(grupoActual.getPassword());

        lvGrupoParticipantes.getItems().clear();
        lvGrupoParticipantes.getItems().addAll(grupoActual.getParticipantes().stream()
                .map(Usuario::getNombre)
                .toList());
        lvGrupoParticipantes.getItems().add(grupoActual.getAdministrador().getNombre());

        lvMensajesGrupo.getItems().clear();
        gestionMensajes.obtenerMensajesDeGrupo(grupoActual)
                .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_OBTENER_MENSAJES_GRUPO, error))
                .peek(mensajesGrupo -> lvMensajesGrupo.getItems().addAll(mensajesGrupo));
    }



    private boolean sonDatosValidos() {
        return !tfAgregarUsuarioGrupo.getText().isEmpty() && grupoActual != null && !grupoActual.isPublico();
    }

    private boolean esAdministradorDelGrupo() {
        return grupoActual.getAdministrador().getNombre().equals(usuario.getNombre())
                && grupoActual.getAdministrador().getPassword().equals(usuario.getPassword());
    }

    private void agregarMiembroSiNoExiste(Usuario nuevoMiembro) {
        if (!grupoActual.getParticipantes().contains(nuevoMiembro)) {
            gestionGrupos.agregarMiembroGrupo(grupoActual, nuevoMiembro)
                    .flatMap(agregado -> gestionGrupos.actualizarGrupo(grupoActual))
                    .peekLeft(error -> mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_GRUPO, error))
                    .peek(success -> {
                        if (success != null && success) {
                            mostrarAlerta(Alert.AlertType.INFORMATION, Constantes.TITULO_USUARIO_AGREGADO, Constantes.CONTENIDO_USUARIO_AGREGADO);
                            actualizarListaParticipantes();
                            tfAgregarUsuarioGrupo.clear();
                        } else {
                            mostrarAlerta(Alert.AlertType.ERROR, Constantes.TITULO_ERROR_GRUPO, Constantes.CONTENIDO_ERROR_GRUPO);
                        }
                    });
        } else {
            mostrarAlerta(Alert.AlertType.INFORMATION, Constantes.TITULO_USUARIO_YA_EXISTE, Constantes.CONTENIDO_USUARIO_YA_EXISTE);
        }
    }

    private void actualizarListaParticipantes() {
        lvGrupoParticipantes.getItems().clear();
        if (grupoActual.getParticipantes() != null) {
            List<String> participantes = grupoActual.getParticipantes().stream()
                    .filter(Objects::nonNull)
                    .map(Usuario::getNombre)
                    .toList();
            lvGrupoParticipantes.getItems().addAll(participantes);
        }
        if (grupoActual.getAdministrador() != null && grupoActual.getAdministrador().getNombre() != null) {
            lvGrupoParticipantes.getItems().add(grupoActual.getAdministrador().getNombre());
        }
        // el usuario actual estara en la lista si es miembro del grupo
        if (usuario != null && !lvGrupoParticipantes.getItems().contains(usuario.getNombre())) {
            lvGrupoParticipantes.getItems().add(usuario.getNombre());
        }
        lvGrupoParticipantes.refresh();
    }

    private void mostrarError(ErrorApp errorText) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);
        alertError.setTitle(Constantes.TITULO_ERROR);
        String errorMensaje = "";
        if (errorText instanceof ErrorAppDataBase e) {
            if (e == ErrorAppDataBase.TIMEOUT || e == ErrorAppDataBase.NO_CONNECTION) {
                errorMensaje = Constantes.CONTENIDO_ERROR_TIMEOUT;
            }
        } else if (errorText instanceof ErrorAppDatosNoValidos e) {
            errorMensaje = e.message();
        }

        alertError.setContentText(errorMensaje);
        alertError.showAndWait();
    }


    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}