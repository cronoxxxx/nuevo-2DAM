package com.example.buzonfxspring_adriansaavedra.domain.service;

import com.example.buzonfxspring_adriansaavedra.dao.impl.DaoMensajesImpl;
import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Mensaje;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GestionMensajes implements IGestionMensajes {
    private final DaoMensajesImpl daoMensajes;

    public GestionMensajes(DaoMensajesImpl daoMensajes) {

        this.daoMensajes = daoMensajes;
    }

    @Override
    public List<Mensaje> obtenerMensajes() {
        return daoMensajes.obtenerMensajes();
    }



    @Override
    public List<Mensaje> obtenerMensajesParaUsuario(Usuario usuario) {
        return daoMensajes.obtenerMensajesParaUsuario(usuario);
    }

    @Override
    public List<Mensaje> obtenerMensajesDeGrupo(Grupo grupo) {
        return daoMensajes.obtenerMensajesDeGrupo(grupo);
    }

    @Override
    public boolean addMensajes(Mensaje mensaje) {
        return daoMensajes.addMensajes(mensaje);
    }

}
