package com.example.miprimeraaplicacionfx_adriansaavedra.dao;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;

import java.util.List;
import java.util.Objects;


public class DaoGruposImpl implements DaoGrupos {
    @Override
    public Grupo accederGrupo(String nombreGrupo, String password) {
        return Grupos.getGrupoList().stream()
                .filter(Objects::nonNull)
                .filter(grupo -> grupo.getNombreGrupo().equals(nombreGrupo) && grupo.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return Grupos.getGrupoList();
    }

    @Override
    public boolean saveGrupos(List<Grupo> gruposList) {
        return Grupos.saveGrupos(gruposList);
    }

    @Override
    public List<Grupo> loadGrupos() {
        return Grupos.getGrupoList();
    }
}