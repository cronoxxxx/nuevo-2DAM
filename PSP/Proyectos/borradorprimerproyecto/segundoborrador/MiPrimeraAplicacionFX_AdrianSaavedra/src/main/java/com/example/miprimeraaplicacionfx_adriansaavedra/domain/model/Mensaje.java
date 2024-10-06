package com.example.miprimeraaplicacionfx_adriansaavedra.domain.model;

import lombok.Data;
/*Estructura oscar : hacer dao de las 3 clases
* private final String texto
* private final LocalDateTime fecha
* private final Usuario owner
* private final List<Usuario> destinatarios
* private final String group*/
@Data
public class Mensaje {

    private String remitente;
    private String texto;


    public Mensaje(String remitente, String texto) {
        this.remitente = remitente;
        this.texto = texto;
    }

    public String toString() {
        return this.remitente + ": " + this.texto;
    }





}
