package com.example.miprimeraaplicacionfx_adriansaavedra.ui;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Mensaje;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.service.GestionGrupos;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.service.GestionUsuarios;
import com.example.miprimeraaplicacionfx_adriansaavedra.ui.model.MainViewModel;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Data
public class PantallaMainViewModel implements Initializable {

    @FXML
    private Label content;
    @FXML
    private TableView<Mensaje> tvBuzonGrupo;
    @FXML
    private TableColumn<Mensaje, String> tcUsuario;
    @FXML
    private TableColumn<Mensaje, String> tcMensaje;
    @FXML
    private Button btnAccederGrupo;
    @FXML
    private Button btnCrearGrupo;
    @FXML
    private TextField tfnuevoNombre;
    @FXML
    private PasswordField tfnuevaContra;
    @FXML
    private TextField tfNombre;
    @FXML
    private PasswordField tfContra;
    @FXML
    private Button btnMensaje;
    @FXML
    private TextArea taMensaje;
    private GestionGrupos gestionGrupos;
    private MainViewModel mainViewModel;
    private GestionUsuarios gestionUsuarios;


    public PantallaMainViewModel() {

        this.gestionUsuarios = new GestionUsuarios();
        this.gestionGrupos = new GestionGrupos();
        mainViewModel = new MainViewModel(null);

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tvBuzonGrupo.setItems(mainViewModel.getMensajesObservableList());
        tcUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcMensaje.setCellValueFactory(new PropertyValueFactory<>("texto"));
        btnMensaje.setDisable(true);
        taMensaje.setDisable(true);
        tvBuzonGrupo.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Actualizar el contenido del label con el mensaje seleccionado
                content.setText(newSelection.getTexto());
            } else {
                // Si no hay selección, limpiar el label
                content.setText("");
            }
        });


    }


    public void accederGrupo() {
        Grupo grupo;
        if (!tfNombre.getText().isEmpty() && !tfContra.getText().isEmpty()) {
            grupo = gestionGrupos.accederGrupo(tfNombre.getText(), tfContra.getText());

            if (grupo != null) {
                btnMensaje.setDisable(false);
                taMensaje.setDisable(false);
                tfContra.clear();
                tfNombre.clear();
                btnAccederGrupo.setDisable(true);
                btnCrearGrupo.setDisable(true);
                tfnuevaContra.clear();
                tfnuevoNombre.clear();

                // Actualizamos mainViewModel con el nuevo grupo
                mainViewModel = new MainViewModel(grupo);

                // Configuramos los items de tvBuzonGrupo
                tvBuzonGrupo.setItems(mainViewModel.getMensajesObservableList());
            }
        }
    }

    public void crearGrupo() {
        if (!tfnuevoNombre.getText().isEmpty() && !tfnuevaContra.getText().isEmpty()) {
            gestionGrupos.obtenerGrupos().add(new Grupo(tfnuevoNombre.getText(), tfnuevaContra.getText()));
            tfnuevaContra.clear();
            tfnuevoNombre.clear();
            gestionGrupos.saveGrupos(gestionGrupos.obtenerGrupos());
        }

    }

    public void enviar() {
        if (!taMensaje.getText().isEmpty()) {
            Grupo actual = mainViewModel.getGrupo();
            Optional<Usuario> usuario = gestionUsuarios.obtenerUsuarios().stream()
                    .filter(Usuario::isIngreso)
                    .findFirst();
            if (usuario.isPresent()) {
                Usuario user = usuario.get();
                Mensaje nuevo = new Mensaje(user.getNickname(), taMensaje.getText());
                actual.getMensajes().add(nuevo);
                tvBuzonGrupo.getItems().add(nuevo);
                gestionGrupos.saveGrupos(gestionGrupos.obtenerGrupos());
                taMensaje.clear();
            }


        }
    }
}
