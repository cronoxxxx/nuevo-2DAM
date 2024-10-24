package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.dao.impl.DaoMensajesImpl;
import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Mensaje;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GestionMensajes implements IGestionMensajes {
    private final DaoMensajesImpl daoMensajes;

    public GestionMensajes(DaoMensajesImpl daoMensajes) {

        this.daoMensajes = daoMensajes;
    }


    @Override
    public Either<String, List<Mensaje>> obtenerMensajes() {

        return daoMensajes.obtenerMensajes();
    }

    @Override
    public Either<String, List<Mensaje>> obtenerMensajesParaUsuario(Usuario usuario) {
        return daoMensajes.obtenerMensajesParaUsuario(usuario);
    }

    @Override
    public Either<String, List<Mensaje>> obtenerMensajesDeGrupo(Grupo grupo) {

        return daoMensajes.obtenerMensajesDeGrupo(grupo);
    }

    @Override
    public Either<String, Boolean> addMensajes(Mensaje mensaje) {

        return daoMensajes.addMensajes(mensaje);
    }
}
