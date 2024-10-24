package com.example.buzonfxspring_adriansaavedra.dao.impl;
import com.example.buzonfxspring_adriansaavedra.common.Constantes;
import com.example.buzonfxspring_adriansaavedra.dao.DaoUsuarios;
import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorApp;
import com.example.buzonfxspring_adriansaavedra.domain.errors.ErrorAppDatosNoValidos;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import io.vavr.control.Either;
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
    public Either<ErrorApp, List<Usuario>> obtenerUsuarios() {
        return usuarios.loadUsuarios()
                .mapLeft(error -> new ErrorAppDatosNoValidos(Constantes.ERROR_AL_OBTENER_USUARIOS + error));
    }

    @Override
    public Either<ErrorApp, Usuario> verificacion(Usuario nickname) {
        return obtenerUsuarios()
                .flatMap(users -> users.stream()
                        .filter(Objects::nonNull)
                        .filter(usr -> usr.getNombre().equals(nickname.getNombre()) && usr.getPassword().equals(nickname.getPassword()))
                        .findFirst()
                        .map(Either::<ErrorApp, Usuario>right)
                        .orElse(Either.left(new ErrorAppDatosNoValidos(Constantes.USUARIO_NO_ENCONTRADO))));
    }

    @Override
    public Either<ErrorApp, Boolean> saveUsuarios(List<Usuario> usuarios) {
        return this.usuarios.saveUsuarios(usuarios);
    }

    @Override
    public Either<ErrorApp, Usuario> buscarUsuarioPorNombre(String nombre) {
        return obtenerUsuarios()
                .flatMap(users -> {
                    saveUsuarios(users);
                    return users.stream()
                            .filter(u -> u != null && u.getNombre() != null && u.getNombre().equals(nombre))
                            .findFirst()
                            .map(Either::<ErrorApp, Usuario>right)
                            .orElse(Either.left(new ErrorAppDatosNoValidos(Constantes.USUARIO_NO_ENCONTRADO)));
                });
    }

    @Override
    public Either<ErrorApp, List<Usuario>> buscarUsuariosPorNombres(List<String> nombres) {
        return obtenerUsuarios()
                .flatMap(users -> {
                    saveUsuarios(users);
                    List<Usuario> foundUsers = users.stream()
                            .filter(u -> u != null && u.getNombre() != null && nombres.contains(u.getNombre()))
                            .toList();
                    return foundUsers.isEmpty()
                            ? Either.left(new ErrorAppDatosNoValidos(Constantes.USUARIOS_NO_ENCONTRADOS))
                            : Either.right(foundUsers);
                });
    }
}
