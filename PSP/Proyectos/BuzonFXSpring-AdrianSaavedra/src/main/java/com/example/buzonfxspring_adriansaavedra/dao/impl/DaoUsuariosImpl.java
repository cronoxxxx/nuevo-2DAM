package com.example.buzonfxspring_adriansaavedra.dao.impl;
import com.example.buzonfxspring_adriansaavedra.dao.DaoUsuarios;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
@Repository
public class DaoUsuariosImpl implements DaoUsuarios {
    private final Database usuarios;
    public DaoUsuariosImpl(Database usuarios) {
        this.usuarios = usuarios;
    }
    @Override
    public List<Usuario> obtenerUsuarios() {
        return this.usuarios.loadUsuarios();
    }


    @Override
    public Usuario verificacion(Usuario nickname) {
        saveUsuarios(obtenerUsuarios());
        return obtenerUsuarios().stream(
                ).filter(Objects::nonNull)
                .filter(u -> u.getNombre().equals(nickname.getNombre()) && u.getPassword().equals(nickname.getPassword()))
                .findFirst()
                .orElse(null
        );
    }

    @Override
    public boolean saveUsuarios(List<Usuario> usuarios) {
        return this.usuarios.saveUsuarios(usuarios);
    }


    @Override
    public boolean addUsuario(Usuario usuario) {
        obtenerUsuarios().add(usuario);
        return saveUsuarios(obtenerUsuarios());
    }

    @Override
    public Usuario buscarUsuarioPorNombre(String nombre) {
        saveUsuarios(obtenerUsuarios());

        return obtenerUsuarios().stream()
                .filter(u -> u != null && u.getNombre() != null)
                .filter(u -> u.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<Usuario> buscarUsuariosPorNombres(List<String> nombres) {
        saveUsuarios(obtenerUsuarios());

        return obtenerUsuarios().stream()
                .filter(usuario -> usuario != null && usuario.getNombre() != null)
                .filter(usuario -> nombres.stream()
                        .anyMatch(nombre -> nombre.equals(usuario.getNombre())))
                .toList();
    }

}
