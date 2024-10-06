package com.example.miprimeraaplicacionfx_adriansaavedra.domain.service;

import com.example.miprimeraaplicacionfx_adriansaavedra.dao.DaoGruposImpl;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;

import java.util.List;

public class GestionGrupos implements IGestionGrupos {
    private final DaoGruposImpl daoGrupos;

    public GestionGrupos() {
        this.daoGrupos = new DaoGruposImpl();
    }



    @Override
    public void actualizarGrupos (Grupo grupo){
        daoGrupos.actualizarGrupo(grupo);
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return daoGrupos.obtenerGrupos();
    }

    @Override
    public boolean saveGrupos(List<Grupo> grupos) {
        return daoGrupos.saveGrupos(grupos);
    }


}
