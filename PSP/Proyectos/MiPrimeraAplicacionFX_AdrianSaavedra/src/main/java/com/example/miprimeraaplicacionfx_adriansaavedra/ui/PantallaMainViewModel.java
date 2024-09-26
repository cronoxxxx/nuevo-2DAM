package com.example.miprimeraaplicacionfx_adriansaavedra.ui;

import com.example.miprimeraaplicacionfx_adriansaavedra.Constantes;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Grupo;
import com.example.miprimeraaplicacionfx_adriansaavedra.dao.Grupos;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Mensaje;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;
@Setter@Getter
public class SegundaPantallaGrupos implements Initializable {
    public MenuItem firstView;
    public MenuItem secondVIew;
    public MenuItem thirdView;
    public MenuBar MainManu;
    public AnchorPane anchorPane;
    public TableView<Mensaje> tvBuzonGrupo;
    public TableColumn<Mensaje, String> tcUsuario;
    public TableColumn<Mensaje, String>  tcMensaje;
    public Button btnAccederGrupo;
    public Button btnCrearGrupo;
    public TextField tfnuevoNombre;
    public PasswordField tfnuevaContra;
    public TextField tfNombre;
    public PasswordField tfContra;
    public Button btnMensaje;
    public TextArea taMensaje;
    private Grupos grupos;
private MainViewModel mainViewModel;


    public SegundaPantallaGrupos() {
        this.grupos = new Grupos();
        mainViewModel = new MainViewModel(null);}

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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainManu.setVisible(true);
    tvBuzonGrupo.setItems(mainViewModel.getMensajesObservableList());

    tcUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
    tcMensaje.setCellValueFactory(new PropertyValueFactory<>("texto"));
    btnMensaje.setDisable(true);
    taMensaje.setDisable(true);


    }


    public void accederGrupo(ActionEvent actionEvent) {
        Grupo grupo = null;
        if (!tfNombre.getText().isEmpty() && !tfContra.getText().isEmpty()) {
            grupo = grupos.accederGrupo(tfNombre.getText(), tfContra.getText());

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

    public void crearGrupo(ActionEvent actionEvent) {
        if (!tfnuevoNombre.getText().isEmpty() && !tfnuevaContra.getText().isEmpty()) {
            grupos.getGrupoList().add(new Grupo(tfnuevoNombre.getText(), tfnuevaContra.getText()));
            tfnuevaContra.clear();
            tfnuevoNombre.clear();
            grupos.saveGrupos(grupos.getGrupoList());
        }

    }
    public void enviar(ActionEvent actionEvent) {
        if (!taMensaje.getText().isEmpty()) {
            Grupo actual = mainViewModel.getGrupo();
            Mensaje nuevo = new Mensaje(Constantes.getUSERNAME(), taMensaje.getText());
            actual.getMensajes().add(nuevo);
            tvBuzonGrupo.getItems().add(nuevo);
            grupos.saveGrupos(grupos.getGrupoList());
            taMensaje.clear();
        }
    }
}
