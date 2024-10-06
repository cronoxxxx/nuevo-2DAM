package com.example.miprimeraaplicacionfx_adriansaavedra.dao;


import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;

import java.util.List;

public interface DaoUsuarios {

    List<Usuario> obtenerUsuarios();
    Usuario getUsuario(String nickname);

    boolean saveUsuarios(List<Usuario> usuarios);
    List<Usuario> loadUsuarios();

    void actualizarUsuario(Usuario usuario);
}
