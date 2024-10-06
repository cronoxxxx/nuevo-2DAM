package com.example.miprimeraaplicacionfx_adriansaavedra.dao;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;

import java.util.List;

public interface DaoGrupos {



    void actualizarGrupo(Grupo grupo);

    List<Grupo> obtenerGrupos();

    boolean saveGrupos(List<Grupo> grupos);

}
