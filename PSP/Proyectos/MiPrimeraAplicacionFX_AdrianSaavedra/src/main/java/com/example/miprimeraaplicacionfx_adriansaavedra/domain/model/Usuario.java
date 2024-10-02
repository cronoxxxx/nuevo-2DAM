package com.example.miprimeraaplicacionfx_adriansaavedra.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
public class Usuario {
    private String nickname;
    private String password;
    private List<Mensaje> mensajesRecibidos;
    private boolean ingreso;


    public Usuario(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
        this.ingreso = false;
        mensajesRecibidos = new ArrayList<>();
    }





}
