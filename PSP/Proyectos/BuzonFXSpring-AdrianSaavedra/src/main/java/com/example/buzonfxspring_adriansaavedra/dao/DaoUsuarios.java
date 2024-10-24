package com.example.buzonfxspring_adriansaavedra.dao;


import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorApp;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import io.vavr.control.Either;

import java.util.List;

public interface DaoUsuarios {

    Either<ErrorApp, List<Usuario>>  obtenerUsuarios();

    Either<ErrorApp, Boolean> saveUsuarios(List<Usuario> usuarios);

    Either<ErrorApp, Usuario> verificacion(Usuario nickname) ;


    Either<ErrorApp, Usuario> buscarUsuarioPorNombre(String nombre);

    Either<ErrorApp, List<Usuario>> buscarUsuariosPorNombres(List<String> nombres);
}
