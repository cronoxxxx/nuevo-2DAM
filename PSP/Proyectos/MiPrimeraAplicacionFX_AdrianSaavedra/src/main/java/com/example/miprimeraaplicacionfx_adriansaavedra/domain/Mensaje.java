package com.example.miprimeraaplicacionfx_adriansaavedra;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Mensaje {

    private String usuario;
    private String texto;

    public Mensaje(String usuario, String texto) {
        this.usuario = usuario;
        this.texto = texto;
    }



}
