package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Mensaje;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;

import java.util.List;

public interface IGestionMensajes {
    List<Mensaje> obtenerMensajes();
    List<Mensaje> obtenerMensajesParaUsuario(Usuario usuario);
    List<Mensaje> obtenerMensajesDeGrupo(Grupo grupo);

    boolean addMensajes(Mensaje mensaje);
}
