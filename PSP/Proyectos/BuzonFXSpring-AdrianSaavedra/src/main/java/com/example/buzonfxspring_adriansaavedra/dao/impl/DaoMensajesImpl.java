package com.example.buzonfxspring_adriansaavedra.dao.impl;

import com.example.buzonfxspring_adriansaavedra.dao.DaoMensajes;
import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Mensaje;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Repository
public class DaoMensajesImpl implements DaoMensajes {
    private final Database mensajeria;

    public DaoMensajesImpl(Database mensajes) {
        this.mensajeria = mensajes;
    }
    @Override
    public List<Mensaje> obtenerMensajes() {
        return mensajeria.loadMensajes();
    }


    @Override
    public List<Mensaje> obtenerMensajesParaUsuario(Usuario usuario) {
        return obtenerMensajes().stream()
                .filter(Objects::nonNull)
                .filter(mensaje -> mensaje.getDestinatarios().stream()
                        .anyMatch(destinatario -> destinatario.getNombre().equals(usuario.getNombre())))
                .toList();
    }
    @Override
    public List<Mensaje> obtenerMensajesDeGrupo(Grupo grupo) {
        return obtenerMensajes().stream()
                .filter(Objects::nonNull)
                .filter(m -> m.getGroup() != null && !m.getGroup().isEmpty())
                .filter(m -> m.getGroup().equals(grupo.getNombre()))
                .toList();
    }

    @Override
    public boolean addMensajes(Mensaje mensaje) {
        List<Mensaje> mensajes = obtenerMensajes();
        if (mensajes == null) {
            mensajes = new ArrayList<>();
        }
        mensajes.add(mensaje);
        return mensajeria.saveMensajes(mensajes);
    }
}
