package com.example.miprimeraaplicacionfx_adriansaavedra.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Getter
@Setter

public class Mensaje {

    private final String texto;
    private LocalDateTime fecha;
    private Usuario owner;
    private List<Usuario> destinatarios;
    private String group;



    public Mensaje(String texto, LocalDateTime fecha, Usuario owner, List<Usuario> destinatarios, String group) {
        this.texto = texto;
        this.fecha = fecha;
        this.owner = owner;
        this.destinatarios = destinatarios;
        this.group = group;
    }
    @Override
    public String toString() {
        return String.format("Texto='%s', Fecha=%s, Enviador=%s}",
                texto, fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), owner.getNombre());
    }

    }



