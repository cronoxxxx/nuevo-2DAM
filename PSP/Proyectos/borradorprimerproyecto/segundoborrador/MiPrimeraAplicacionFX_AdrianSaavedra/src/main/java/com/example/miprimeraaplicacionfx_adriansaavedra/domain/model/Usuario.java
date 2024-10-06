package com.example.miprimeraaplicacionfx_adriansaavedra.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Usuario {
    private String nombre;
    private String password;
    private List<String> gruposSecretos;
    private List<String>gruposPublicos;
    private List<Mensaje> mensajesRecibidos;
    private boolean ingreso;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
        this.gruposSecretos = new ArrayList<>();
        this.mensajesRecibidos = new ArrayList<>();
        this.gruposPublicos = new ArrayList<>();
        this.ingreso = false;
    }

    public void agregarGrupoPublico(String grupoPublico) {
        if (!gruposPublicos.contains(grupoPublico)) {
            gruposPublicos.add(grupoPublico);
        }
    }

    public void agregarGrupoSecreto(String grupoSecreto) {
        if (!gruposSecretos.contains(grupoSecreto)) {
            gruposSecretos.add(grupoSecreto);
        }
    }





}
