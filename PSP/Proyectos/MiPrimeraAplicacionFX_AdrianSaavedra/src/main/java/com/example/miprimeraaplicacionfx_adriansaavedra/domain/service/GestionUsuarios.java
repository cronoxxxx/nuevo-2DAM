package com.example.miprimeraaplicacionfx_adriansaavedra.domain.service;

import com.example.miprimeraaplicacionfx_adriansaavedra.dao.impl.DaoUsuariosImpl;
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
    public boolean saveUsuarios(List<Usuario> usuarios) {
        return dao.saveUsuarios(usuarios);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        dao.actualizarUsuario(usuario);

    }

    @Override
    public Usuario verificacion(Usuario nickname) {
        return dao.verificacion(nickname);
    }
    @Override
    public boolean addUsuario(Usuario usuario) {
        return dao.addUsuario(usuario);

    }
    @Override
    public Usuario buscarUsuarioPorNombre(String nombre){
        return dao.buscarUsuarioPorNombre(nombre);
    }
    @Override
    public List<Usuario> buscarUsuariosPorNombres(List<String> nombres){
        return dao.buscarUsuariosPorNombres(nombres);
    }
}