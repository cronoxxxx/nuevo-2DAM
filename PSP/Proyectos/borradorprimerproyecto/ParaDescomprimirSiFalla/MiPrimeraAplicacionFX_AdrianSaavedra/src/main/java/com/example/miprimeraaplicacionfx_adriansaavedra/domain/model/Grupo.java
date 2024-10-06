package com.example.miprimeraaplicacionfx_adriansaavedra.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter@Setter
public class Grupo {
    private String nombreGrupo;
    private String password;
    private List<Mensaje> mensajes;

    public Grupo(String nombreGrupo, String password) {
        this.nombreGrupo = nombreGrupo;
        this.password = password;
        this.mensajes = new ArrayList<>();
    }

}
