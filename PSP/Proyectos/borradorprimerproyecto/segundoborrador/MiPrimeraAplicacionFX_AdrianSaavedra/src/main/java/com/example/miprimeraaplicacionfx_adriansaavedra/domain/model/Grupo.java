package com.example.miprimeraaplicacionfx_adriansaavedra.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/*
* private final List<Usuario> participantes
* private final String nombre
* private final String password
* private final Usuario administrador
* private final boolean publico
* private final LocalDateTime fechaCreacion
* */

@Getter@Setter
public class Grupo {
    private String nombre;
    private String password;
    private boolean esPublico;
    private List<String> participantes; //para añadir participantes no repetidos
    private List<Mensaje> mensajes;

    public Grupo(String nombre, String password, boolean esPublico) {
        this.nombre = nombre;
        this.password = password;
        this.esPublico = esPublico;
        this.mensajes = new ArrayList<>();
        this.participantes = new ArrayList<>();
    }

    public void agregarMiembro(String miembro) {
        if (!participantes.contains(miembro)) {
            participantes.add(miembro);
        }
    }

    public void agregarMensaje(Mensaje mensaje) {
        mensajes.add(mensaje);
    }


}
