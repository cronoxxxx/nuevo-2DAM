package com.example.miprimeraaplicacionfx_adriansaavedra.domain.model;

import lombok.Data;
@Data
public class Mensaje {

    private String usuario;
    private String texto;

    public Mensaje(String usuario, String texto) {
        this.usuario = usuario;
        this.texto = texto;
    }




}
