package com.example.miprimeraaplicacionfx_adriansaavedra.ui.model;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

@Data
public class MainViewModel {
    private Grupo grupo;
    private ObservableList<Mensaje> mensajesObservableList;

    public  MainViewModel(Grupo grupo) {
        this.grupo = grupo;
        this.mensajesObservableList = FXCollections.observableArrayList();
        if (grupo != null && grupo.getMensajes() != null) {
            this.mensajesObservableList.addAll(grupo.getMensajes());
        }
    }

}
