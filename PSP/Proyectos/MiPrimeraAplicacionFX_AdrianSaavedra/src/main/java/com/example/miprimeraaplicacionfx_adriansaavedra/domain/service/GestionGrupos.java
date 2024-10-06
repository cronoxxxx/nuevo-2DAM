package com.example.miprimeraaplicacionfx_adriansaavedra.domain.service;

import com.example.miprimeraaplicacionfx_adriansaavedra.dao.impl.DaoGruposImpl;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;

import java.util.List;

public class GestionGrupos implements IGestionGrupos {
    private final DaoGruposImpl daoGrupos;

    public GestionGrupos() {
        this.daoGrupos = new DaoGruposImpl();
    }


    @Override
    public void actualizarGrupo(Grupo grupo) {
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

    @Override
    public List<String> obtenerGruposParaUsuario(String nombreUsuario, boolean publico) {
        return daoGrupos.obtenerGruposParaUsuario(nombreUsuario, publico);
    }
    @Override
    public Grupo obtenerGrupoPorNombre(String nombreGrupo) {
        return daoGrupos.obtenerGrupoPorNombre(nombreGrupo);
    }
    @Override
    public Grupo ingresar(Grupo grupo){
        return daoGrupos.ingresar(grupo);
    }
    @Override
    public boolean addGroup(Grupo grupo){
        return daoGrupos.addGroup(grupo);
    }

    @Override
    public List<String> obtenerParticipantesPublicos(Grupo grupo) {
        return daoGrupos.obtenerParticipantesPublicos(grupo);
    }
}
