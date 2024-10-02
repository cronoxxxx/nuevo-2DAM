package com.example.hospitalcrud.dao.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Patient {

    private int id;
    private String name;
    private String phone;
    private Credential credential;
    private LocalDate birthday;


    public Patient(int id, String name, String phone, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
    }

}
