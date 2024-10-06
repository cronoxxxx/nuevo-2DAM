package com.example.miprimeraaplicacionfx_adriansaavedra.dao;

import com.example.miprimeraaplicacionfx_adriansaavedra.common.Configuracion;
import com.example.miprimeraaplicacionfx_adriansaavedra.domain.model.Usuario;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    @Getter
    private static final List<Usuario> usuarioList;

    static {
        usuarioList = loadUsuarios();
    }

    private static List<Usuario> loadUsuarios() {
        Gson gson = new GsonBuilder().create();
        Type userListType = new TypeToken<ArrayList<Usuario>>() {}.getType();

        List<Usuario> usuarios;
        try {
            usuarios = gson.fromJson(
                    new FileReader(new Configuracion().loadPathProperties()),
                    userListType);
        } catch (FileNotFoundException e) {
           throw new IllegalArgumentException();
        }
        return usuarios;
    }

    public static boolean saveUsuarios(List<Usuario> usuarios) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fw = new FileWriter(new Configuracion().loadPathProperties())) {
            gson.toJson(usuarios, fw);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}

