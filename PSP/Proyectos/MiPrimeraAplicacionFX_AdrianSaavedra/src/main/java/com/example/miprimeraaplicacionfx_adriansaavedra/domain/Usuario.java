package com.example.miprimeraaplicacionfx_adriansaavedra;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter@Setter
public class Usuario {
    private String nickname, password;
    private List<Mensaje> mensajesRecibidos;

    private int id;
    private static int autoId = 0;

    public Usuario(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;

        mensajesRecibidos = new ArrayList<>();
        this.id = ++autoId;
    }



}
