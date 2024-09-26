package com.example.miprimeraaplicacionfx_adriansaavedra;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter@Setter
public class Grupo {

    private int id;
    private String nombreGrupo,password;
    private static int autoId = 0;
    private List<Mensaje> mensajes;

    public Grupo(String nombreGrupo, String password) {
        this.nombreGrupo = nombreGrupo;
        this.password = password;
        this.id = ++autoId;
        this.mensajes = new ArrayList<>();
    }

}
