package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.dao.impl.DaoUsuariosImpl;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import com.example.buzonfxspring_adriansaavedra.domain.validators.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GestionUsuarios implements IGestionUsuarios {
    private final DaoUsuariosImpl dao;
    private final UserValidator userValidator;

    public GestionUsuarios(DaoUsuariosImpl dao, UserValidator userValidator) {

        this.dao = dao;
        this.userValidator = userValidator;
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
    public Usuario verificacion(Usuario nickname) {
        if (userValidator.validateUser(nickname)) {
            return dao.verificacion(nickname);
        } else {
            return null;
        }
    }

    @Override
    public boolean addUsuario(Usuario usuario) {
        List<Usuario> usuarios = obtenerUsuarios();
        if (userValidator.validateUser(usuario)) {
            usuarios.add(usuario);
            return saveUsuarios(usuarios);
        } else {
            return false;
        }
    }

    @Override
    public Usuario buscarUsuarioPorNombre(String nombre) {
        return dao.buscarUsuarioPorNombre(nombre);
    }

    @Override
    public List<Usuario> buscarUsuariosPorNombres(List<String> nombres) {
        return dao.buscarUsuariosPorNombres(nombres);
    }
}