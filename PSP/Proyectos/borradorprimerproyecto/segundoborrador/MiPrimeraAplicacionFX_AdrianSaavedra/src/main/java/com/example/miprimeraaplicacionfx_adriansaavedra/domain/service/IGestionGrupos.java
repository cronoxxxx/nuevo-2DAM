package com.example.miprimeraaplicacionfx_adriansaavedra.domain.service;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;


import java.util.List;

public interface IGestionGrupos {

    void actualizarGrupos(Grupo grupo);

    List<Grupo> obtenerGrupos();

    boolean saveGrupos(List<Grupo> grupos);


}
