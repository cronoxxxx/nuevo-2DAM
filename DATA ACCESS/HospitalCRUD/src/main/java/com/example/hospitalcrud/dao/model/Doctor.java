package com.example.hospitalcrud.dao.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data@Getter@Setter
public class Doctor {
    private int id;
    private String name;

    public Doctor(int i, String doctor2) {
        this.id = i;
        this.name = doctor2;
    }
}
