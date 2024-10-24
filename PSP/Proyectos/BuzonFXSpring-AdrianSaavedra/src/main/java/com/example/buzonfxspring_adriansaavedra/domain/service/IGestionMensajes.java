package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Mensaje;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import io.vavr.control.Either;

import java.util.List;

public interface IGestionMensajes {
    Either<String, List<Mensaje>> obtenerMensajes();
    Either<String, List<Mensaje>> obtenerMensajesParaUsuario(Usuario usuario);
    Either<String, List<Mensaje>> obtenerMensajesDeGrupo(Grupo grupo);

    Either<String, Boolean> addMensajes(Mensaje mensaje);
}
