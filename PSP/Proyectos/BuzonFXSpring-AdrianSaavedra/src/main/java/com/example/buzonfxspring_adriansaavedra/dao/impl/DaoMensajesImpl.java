package com.example.buzonfxspring_adriansaavedra.dao.impl;

import com.example.buzonfxspring_adriansaavedra.common.Constantes;
import com.example.buzonfxspring_adriansaavedra.dao.DaoMensajes;
import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Mensaje;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import io.vavr.control.Either;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;

@Repository
public class DaoMensajesImpl implements DaoMensajes {
    private final Database mensajeria;

    public DaoMensajesImpl(Database mensajes) {
        this.mensajeria = mensajes;
    }

    @Override
    public Either<String, List<Mensaje>> obtenerMensajes() {
        return mensajeria.loadMensajes().mapLeft(error -> Constantes.ERROR_AL_OBTENER_MENSAJES + error);
    }


    @Override
    public Either<String, List<Mensaje>> obtenerMensajesParaUsuario(Usuario usuario) {
        return obtenerMensajes()
                .map(mensajes -> mensajes.stream()
                        .filter(Objects::nonNull)
                        .filter(mensaje -> mensaje.getDestinatarios().stream()
                                .anyMatch(destinatario -> destinatario.getNombre().equals(usuario.getNombre())))
                        .toList())
                .flatMap(mensajes -> mensajes.isEmpty()
                        ? Either.left(Constantes.NO_HAY_MENSAJES_PARA_USUARIO)
                        : Either.right(mensajes));
    }

    @Override
    public Either<String, List<Mensaje>> obtenerMensajesDeGrupo(Grupo grupo) {
        return obtenerMensajes()
                .map(mensajes -> mensajes.stream()
                        .filter(Objects::nonNull)
                        .filter(m -> m.getGroup() != null && !m.getGroup().isEmpty())
                        .filter(m -> m.getGroup().equals(grupo.getNombre()))
                        .toList())
                .flatMap(mensajes -> mensajes.isEmpty()
                        ? Either.left(Constantes.NO_HAY_MENSAJES_PARA_GRUPO)
                        : Either.right(mensajes));
    }

    @Override
    public Either<String, Boolean> addMensajes(Mensaje mensaje) {
        return obtenerMensajes()
                .flatMap(mensajes -> {
                    mensajes.add(mensaje);
                    return mensajeria.saveMensajes(mensajes);
                })
                .mapLeft(error -> Constantes.ERROR_AL_AGREGAR_MENSAJE + error);
    }
}
