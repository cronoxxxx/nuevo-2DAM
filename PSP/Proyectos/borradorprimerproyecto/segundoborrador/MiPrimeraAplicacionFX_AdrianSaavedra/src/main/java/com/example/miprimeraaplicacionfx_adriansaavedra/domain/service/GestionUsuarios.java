package com.example.miprimeraaplicacionfx_adriansaavedra.domain.service;

import com.example.miprimeraaplicacionfx_adriansaavedra.dao.DaoUsuariosImpl;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;

import java.util.List;

public class GestionUsuarios implements IGestionUsuarios {
    private final DaoUsuariosImpl dao;

    public GestionUsuarios() {
        this.dao = new DaoUsuariosImpl();
    }
    @Override
    public List<Usuario> obtenerUsuarios() {
        return dao.obtenerUsuarios();
    }

    @Override
    public Usuario getUsuario(String nickname) {
        return dao.getUsuario(nickname);
    }

    @Override
    public boolean saveUsuarios(List<Usuario> usuarios) {
        return dao.saveUsuarios(usuarios);
    }

    @Override
    public List<Usuario> loadUsuarios() {
        return dao.loadUsuarios();
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        dao.actualizarUsuario(usuario);
    }
}