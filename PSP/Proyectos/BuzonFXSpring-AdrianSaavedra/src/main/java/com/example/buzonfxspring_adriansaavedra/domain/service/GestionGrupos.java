package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.common.Constantes;
import com.example.buzonfxspring_adriansaavedra.dao.impl.DaoGruposImpl;
import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import com.example.buzonfxspring_adriansaavedra.domain.validators.GrupoValidator;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GestionGrupos implements IGestionGrupos {
    private final DaoGruposImpl daoGrupos;
    private final GrupoValidator grupoValidator;

    public GestionGrupos(DaoGruposImpl daoGrupos, GrupoValidator grupoValidator) {
        this.daoGrupos = daoGrupos;
        this.grupoValidator = grupoValidator;
    }

    @Override
    public Either<String, Boolean> actualizarGrupo(Grupo grupo) {
        return grupoValidator.validateGrupo(grupo)
                .flatMap(valid -> daoGrupos.actualizarGrupo(grupo))
                .mapLeft(error -> Constantes.GRUPO_NO_VALIDO);
    }


    @Override
    public Either<String, List<Grupo>> obtenerGrupos() {
        return daoGrupos.obtenerGrupos();
    }

    @Override
    public Either<String, Boolean> saveGrupos(List<Grupo> grupos) {
        return daoGrupos.saveGrupos(grupos);
    }

    @Override
    public Either<String, List<String>> obtenerGruposParaUsuario(String nombreUsuario, boolean publico) {
        return daoGrupos.obtenerGruposParaUsuario(nombreUsuario, publico);
    }

    @Override
    public Either<String, Grupo> obtenerGrupoPorNombre(String nombreGrupo) {
        return daoGrupos.obtenerGrupoPorNombre(nombreGrupo);
    }

    @Override
    public Either<String, Grupo> ingresar(Grupo grupo) {
        return grupoValidator.validateGrupo(grupo)
                .flatMap(valid -> daoGrupos.ingresar(grupo))
                .mapLeft(error -> Constantes.GRUPO_NO_VALIDO);
    }

    @Override
    public Either<String, Boolean> addGroup(Grupo grupo) {
        return grupoValidator.validateGrupo(grupo)
                .flatMap(valid -> grupo.getAdministrador() == null
                        ? Either.left(Constantes.GRUPO_NO_VALIDO)
                        : obtenerGrupos().flatMap(grupos -> {
                    grupos.add(grupo);
                    return saveGrupos(grupos);
                }))
                .mapLeft(error -> Constantes.GRUPO_NO_VALIDO);
    }

    @Override
    public Either<String, Boolean> agregarMiembroGrupo(Grupo grupo, Usuario miembro) {
        return grupoValidator.validateGrupo(grupo)
                .flatMap(valid -> daoGrupos.agregarMiembroGrupo(grupo, miembro))
                .mapLeft(error -> Constantes.GRUPO_NO_VALIDO);
    }
}