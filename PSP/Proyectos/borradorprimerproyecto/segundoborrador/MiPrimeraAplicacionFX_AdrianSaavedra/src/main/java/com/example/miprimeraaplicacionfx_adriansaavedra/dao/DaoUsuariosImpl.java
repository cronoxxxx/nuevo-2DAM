package com.example.miprimeraaplicacionfx_adriansaavedra.dao;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;

import java.util.List;
import java.util.Objects;

public class DaoUsuariosImpl implements DaoUsuarios {
    private final Usuarios usuarios;
    public DaoUsuariosImpl() {
        this.usuarios = new Usuarios();
    }
    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarios.getUsuarioList();
    }

    @Override
    public Usuario getUsuario(String nickname) {
        return usuarios.getUsuarioList().stream(
                ).filter(Objects::nonNull)
                .filter(u -> u.getNombre().equals(nickname))
                .findFirst()
                .orElse(null
        );
    }

    @Override
    public boolean saveUsuarios(List<Usuario> usuarios) {
        return this.usuarios.saveUsuarios(usuarios);
    }

    @Override
    public List<Usuario> loadUsuarios() {
        return this.usuarios.loadUsuarios();
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarios.getUsuarioList().stream()
              .filter(u -> u.getNombre().equals(usuario.getNombre()))
                .findFirst()
                .ifPresent(u -> {
                    int index = usuarios.getUsuarioList().indexOf(u);
                    usuarios.getUsuarioList().set(index, usuario);
                });
        saveUsuarios(usuarios.getUsuarioList());
    }
}
