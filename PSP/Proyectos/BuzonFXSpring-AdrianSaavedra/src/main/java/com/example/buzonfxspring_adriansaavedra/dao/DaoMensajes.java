package com.example.buzonfxspring_adriansaavedra.dao;

import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Mensaje;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;

import java.util.List;


import io.vavr.control.Either;

public interface DaoMensajes {

    Either<String, List<Mensaje>> obtenerMensajes();
    Either<String, List<Mensaje>> obtenerMensajesParaUsuario(Usuario usuario);

    Either<String, List<Mensaje>> obtenerMensajesDeGrupo(Grupo grupo);

    Either<String, Boolean> addMensajes(Mensaje mensaje);
}