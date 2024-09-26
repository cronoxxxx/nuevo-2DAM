package com.example.miprimeraaplicacionfx_adriansaavedra;

import com.example.miprimeraaplicacionfx_adriansaavedra.domain.Usuario;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter@Getter
public class Usuarios {
    private final List<Usuario> usuarioList;

    public Usuarios() {
        usuarioList = loadUsuarios();
    }

    public Usuario getUsuario(String nickname) {
        return usuarioList.stream().filter(Objects::nonNull).filter(u -> u.getNickname().equals(nickname)).findFirst().orElse(null);
    }
    public List<Usuario> loadUsuarios() {
        Gson gson = new GsonBuilder().create();
        Type userListType = new TypeToken<ArrayList<Usuario>>() {}.getType();

        List<Usuario> usuarios = null;
        try {
            usuarios = gson.fromJson(
                    new FileReader(new Configuracion().loadPathProperties() ),
                    userListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public boolean saveUsuarios(List<Usuario> usuarios) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter fw = new FileWriter(new Configuracion().loadPathProperties())) {
            gson.toJson(usuarios, fw);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }






}
