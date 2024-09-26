package com.example.miprimeraaplicacionfx_adriansaavedra.screens;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Grupo;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Mensaje;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
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
