package com.example.buzonfxspring_adriansaavedra.dao;


import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;

import java.util.List;

public interface DaoUsuarios {

    List<Usuario> obtenerUsuarios();

    boolean saveUsuarios(List<Usuario> usuarios);

    Usuario verificacion(Usuario nickname);


    Usuario buscarUsuarioPorNombre(String nombre);

    List<Usuario> buscarUsuariosPorNombres(List<String> nombres);
}
