package com.example.buzonfxspring_adriansaavedra.dao;

import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import io.vavr.control.Either;

import java.util.List;

public interface DaoGrupos {



    Either<String, Boolean> actualizarGrupo(Grupo grupo);

    Either<String, List<Grupo>> obtenerGrupos();

    Either<String, Boolean> saveGrupos(List<Grupo> grupos);
    Either<String, List<String>> obtenerGruposParaUsuario(String nombreUsuario, boolean publico);

    Either<String, Grupo> obtenerGrupoPorNombre(String nombreGrupo);

    Either<String, Grupo> ingresar(Grupo grupo);

    Either<String, Boolean> agregarMiembroGrupo(Grupo grupo, Usuario miembro);
}
