package com.example.miprimeraaplicacionfx_adriansaavedra.dao;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;

import java.util.List;
import java.util.Objects;

public class DaoUsuariosImpl implements DaoUsuarios {
    @Override
    public List<Usuario> obtenerUsuarios() {
        return Usuarios.getUsuarioList();
    }

    @Override
    public Usuario getUsuario(String nickname) {
        return Usuarios.getUsuarioList().stream()
                .filter(Objects::nonNull)
                .filter(u -> u.getNickname().equals(nickname))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean saveUsuarios(List<Usuario> usuarios) {
        return Usuarios.saveUsuarios(usuarios);
    }

    @Override
    public List<Usuario> loadUsuarios() {
        return Usuarios.getUsuarioList();
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        Usuarios.getUsuarioList().stream()
                .filter(u -> u.getNickname().equals(usuario.getNickname()))
                .findFirst()
                .ifPresent(u -> {
                    int index = Usuarios.getUsuarioList().indexOf(u);
                    Usuarios.getUsuarioList().set(index, usuario);
                });
        saveUsuarios(Usuarios.getUsuarioList());
    }
}
