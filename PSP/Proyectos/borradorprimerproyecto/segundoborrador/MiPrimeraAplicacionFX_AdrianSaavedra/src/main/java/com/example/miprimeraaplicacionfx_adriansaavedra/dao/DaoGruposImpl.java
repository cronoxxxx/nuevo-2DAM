package com.example.miprimeraaplicacionfx_adriansaavedra.dao;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;

import java.util.List;



public class DaoGruposImpl implements DaoGrupos {
    private final Grupos grupos;

    public DaoGruposImpl() {
        this.grupos = new Grupos();
    }


    @Override
    public void actualizarGrupo(Grupo grupo) {
        grupos.getGrupoList().stream()
                .filter(g -> g.getNombre().equals(grupo.getNombre()))
                .findFirst()
                .ifPresent(g -> {
                    int index = grupos.getGrupoList().indexOf(g);
                    grupos.getGrupoList().set(index, grupo);
                });
        saveGrupos(grupos.getGrupoList());
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return grupos.getGrupoList();
    }

    @Override
    public boolean saveGrupos(List<Grupo> grupos) {
        return this.grupos.saveGrupos(grupos);
    }


}