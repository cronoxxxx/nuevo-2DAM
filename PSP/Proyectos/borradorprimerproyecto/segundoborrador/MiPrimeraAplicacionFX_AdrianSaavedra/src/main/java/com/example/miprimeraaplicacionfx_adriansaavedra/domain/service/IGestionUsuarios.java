package com.example.miprimeraaplicacionfx_adriansaavedra.domain.service;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;

import java.util.List;

public interface IGestionUsuarios {
    List<Usuario> obtenerUsuarios();
    Usuario getUsuario(String nickname);

    boolean saveUsuarios(List<Usuario> usuarios);
    List<Usuario> loadUsuarios();

    void actualizarUsuario(Usuario usuario);
}
