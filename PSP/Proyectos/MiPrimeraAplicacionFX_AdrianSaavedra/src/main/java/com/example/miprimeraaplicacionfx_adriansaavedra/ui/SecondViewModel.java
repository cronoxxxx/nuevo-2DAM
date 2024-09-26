package com.example.miprimeraaplicacionfx_adriansaavedra.screens;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Mensaje;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SecondViewModel {


    private Usuario usuario;
    private ObservableList<Mensaje> mensajesObservableList;

    public SecondViewModel(Usuario usuario) {
        this.usuario = usuario;
        this.mensajesObservableList = FXCollections.observableArrayList();
        if (usuario != null && usuario.getMensajesRecibidos() != null) {
            this.mensajesObservableList.addAll(usuario.getMensajesRecibidos());
        }
    }
}
