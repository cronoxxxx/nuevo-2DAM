package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.dao.impl.DaoGruposImpl;
import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import com.example.buzonfxspring_adriansaavedra.domain.validators.GrupoValidator;
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
    public boolean actualizarGrupo(Grupo grupo) {
       return daoGrupos.actualizarGrupo(grupo);
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return daoGrupos.obtenerGrupos();
    }

    @Override
    public boolean saveGrupos(List<Grupo> grupos) {
        return daoGrupos.saveGrupos(grupos);
    }

    @Override
    public List<String> obtenerGruposParaUsuario(String nombreUsuario, boolean publico) {
        return daoGrupos.obtenerGruposParaUsuario(nombreUsuario, publico);
    }

    @Override
    public Grupo obtenerGrupoPorNombre(String nombreGrupo) {
        return daoGrupos.obtenerGrupoPorNombre(nombreGrupo);
    }

    @Override
    public Grupo ingresar(Grupo grupo) {
        if (grupoValidator.validateGrupo(grupo)) {
            return daoGrupos.ingresar(grupo);
        } else {
            return null;
        }
    }

    @Override
    public boolean addGroup(Grupo grupo) {
        List<Grupo> grupos = obtenerGrupos();
        if (grupoValidator.validateGrupo(grupo) && grupo.getAdministrador() != null) {
            grupos.add(grupo);
            return saveGrupos(grupos);
        } else {
            return false;
        }
    }


    @Override
    public boolean agregarMiembroGrupo(Grupo grupo, Usuario miembro) {
        if (grupoValidator.validateGrupo(grupo)) {
            return daoGrupos.agregarMiembroGrupo(grupo, miembro);
        } else {
            return false;
        }

    }

}
