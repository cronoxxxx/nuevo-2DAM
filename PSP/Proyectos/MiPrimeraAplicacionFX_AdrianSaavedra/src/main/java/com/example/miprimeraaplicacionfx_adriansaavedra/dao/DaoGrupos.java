package com.example.miprimeraaplicacionfx_adriansaavedra.dao;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;

import java.util.List;

public interface DaoGrupos {

    Grupo accederGrupo(String nombreGrupo, String password);

    List<Grupo> obtenerGrupos();

    boolean saveGrupos(List<Grupo> grupos);

    List<Grupo> loadGrupos();
}
