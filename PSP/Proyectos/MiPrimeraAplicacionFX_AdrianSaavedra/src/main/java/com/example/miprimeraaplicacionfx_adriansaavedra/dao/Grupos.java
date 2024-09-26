package com.example.miprimeraaplicacionfx_adriansaavedra;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Grupo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Setter@Getter
public class Grupos {

    private final List<Grupo> grupoList;


    public Grupos() {
        grupoList = loadGrupos();
    }




    public Grupo accederGrupo(String nombreGrupo, String password) {
        return grupoList.stream()
                .filter(Objects::nonNull)  // Filter only Grupo objects
                .filter(grupo -> grupo.getNombreGrupo().equals(nombreGrupo) && grupo.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public List<Grupo> loadGrupos() {
        Gson gson = new GsonBuilder().create();
        Type userListType = new TypeToken<List<Grupo>   >() {}.getType();

        List<Grupo> grupos = null;
        try {
            grupos = gson.fromJson(
                    new FileReader(new Configuracion().loadPathPropertiesGroup() ),
                    userListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    public boolean saveGrupos(List<Grupo> grupos) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fw = new FileWriter(new Configuracion().loadPathPropertiesGroup())) {
            gson.toJson(grupos, fw);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



}
