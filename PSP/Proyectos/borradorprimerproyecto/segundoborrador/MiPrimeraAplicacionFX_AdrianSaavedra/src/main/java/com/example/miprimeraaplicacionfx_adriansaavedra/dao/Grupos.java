package com.example.miprimeraaplicacionfx_adriansaavedra.dao;

import com.example.miprimeraaplicacionfx_adriansaavedra.common.Configuracion;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Grupo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Getter
public class Grupos {
    private final List<Grupo> grupoList;

    public Grupos() {
        grupoList = loadGrupos();

    }

    public List<Grupo> loadGrupos() {
        Gson gson = new GsonBuilder().create();
        Type userListType = new TypeToken<List<Grupo>>() {}.getType();

        List<Grupo> grupos;
        try {
            grupos = gson.fromJson(
                    new FileReader(new Configuracion().loadPathPropertiesGroup()),
                    userListType);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException();
        }
        return grupos;
    }

    public boolean saveGrupos(List<Grupo> grupos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fw = new FileWriter(new Configuracion().loadPathPropertiesGroup())) {
            gson.toJson(grupos, fw);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}