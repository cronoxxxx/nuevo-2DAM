package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;

import java.util.List;

public interface IGestionUsuarios {
    List<Usuario> obtenerUsuarios();

    boolean saveUsuarios(List<Usuario> usuarios);
    Usuario verificacion(Usuario nickname);

    boolean addUsuario(Usuario usuario);

    Usuario buscarUsuarioPorNombre(String nombre);

    List<Usuario> buscarUsuariosPorNombres(List<String> nombres);
}
