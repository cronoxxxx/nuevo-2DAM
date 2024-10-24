package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.common.Constantes;
import com.example.buzonfxspring_adriansaavedra.dao.impl.DaoUsuariosImpl;
import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorApp;
import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorAppDatosNoValidos;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import com.example.buzonfxspring_adriansaavedra.domain.validators.UserValidator;
import io.vavr.control.Either;
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
    public Either<ErrorApp, List<Usuario>> obtenerUsuarios() {
        return dao.obtenerUsuarios();
    }


    @Override
    public Either<ErrorApp, Boolean> saveUsuarios(List<Usuario> usuarios) {
        return dao.saveUsuarios(usuarios);
    }



    @Override
    public Either<ErrorApp, Usuario> verificacion(Usuario nickname) {
        return userValidator.validateUser(nickname)
                .flatMap(valid -> dao.verificacion(nickname))
                .mapLeft(error -> new ErrorAppDatosNoValidos(Constantes.USUARIO_NO_VALIDO));
    }
    @Override
    public Either<ErrorApp, Boolean> addUsuario(Usuario usuario) {
        return userValidator.validateUser(usuario)
                .flatMap(valid -> obtenerUsuarios()
                        .flatMap(usuarios -> {
                            usuarios.add(usuario);
                            return saveUsuarios(usuarios);
                        }))
                .mapLeft(error -> new ErrorAppDatosNoValidos(Constantes.USUARIO_NO_VALIDO));
    }


    @Override
    public Either<ErrorApp, Usuario> buscarUsuarioPorNombre(String nombre) {
        return dao.buscarUsuarioPorNombre(nombre);
    }

    @Override
    public Either<ErrorApp, List<Usuario>> buscarUsuariosPorNombres(List<String> nombres) {
        return dao.buscarUsuariosPorNombres(nombres);
    }
}