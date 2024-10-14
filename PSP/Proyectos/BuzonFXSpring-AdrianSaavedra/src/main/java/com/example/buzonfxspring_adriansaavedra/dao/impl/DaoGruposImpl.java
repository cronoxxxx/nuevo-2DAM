package com.example.buzonfxspring_adriansaavedra.dao.impl;

import com.example.buzonfxspring_adriansaavedra.dao.DaoGrupos;
import com.example.buzonfxspring_adriansaavedra.domain.model.Grupo;
import com.example.buzonfxspring_adriansaavedra.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class DaoGruposImpl implements DaoGrupos {
    private final Database grupos;

    public DaoGruposImpl(Database grupos) {

        this.grupos = grupos;
    }
    @Override
    public boolean actualizarGrupo(Grupo grupo) {
        List<Grupo> grupoList = obtenerGrupos();
        for (int i = 0; i < grupoList.size(); i++) {
            if (grupoList.get(i).getNombre().equals(grupo.getNombre())) {
                grupoList.set(i, grupo);
                return saveGrupos(grupoList);
            }
        }
        return false;
    }

    @Override
    public List<Grupo> obtenerGrupos() {
        return grupos.loadGrupos();
    }

    @Override
    public boolean saveGrupos(List<Grupo> grupos) {
        return this.grupos.saveGrupos(grupos);
    }
    @Override
    public List<String> obtenerGruposParaUsuario(String nombreUsuario, boolean publico) {
        saveGrupos(obtenerGrupos());
        return obtenerGrupos().stream()
                .filter(grupo -> grupo.isPublico() == publico)
                .filter(grupo -> grupo.getParticipantes().stream()
                        .anyMatch(participante -> participante.getNombre().equals(nombreUsuario)) ||
                        grupo.getAdministrador().getNombre().equals(nombreUsuario))
                .map(Grupo::getNombre).toList();
    }
    @Override
    public Grupo obtenerGrupoPorNombre(String nombreGrupo) {
        saveGrupos(obtenerGrupos());
        return obtenerGrupos().stream()
                .filter(g -> g != null && g.getNombre() != null)
                .filter(g -> g.getNombre().equals(nombreGrupo))
                .findFirst()
                .orElse(null);
    }
    @Override
    public Grupo ingresar(Grupo grupo) {
        saveGrupos(obtenerGrupos());
        return obtenerGrupos().stream()
                .filter(g -> g != null && g.getNombre() != null && g.getPassword() != null)
                .filter(g -> g.getNombre().equals(grupo.getNombre()) && g.getPassword().equals(grupo.getPassword()) && g.isPublico() == grupo.isPublico())
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean agregarMiembroGrupo(Grupo grupo, Usuario miembro) {
        boolean exists = grupo.getParticipantes().stream()
                .anyMatch(p -> p.getNombre().equals(miembro.getNombre())
                        && p.getPassword().equals(miembro.getPassword()));
        if (!exists) {
            grupo.getParticipantes().add(miembro);
        }
        return exists;
    }
}