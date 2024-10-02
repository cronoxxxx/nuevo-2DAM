package com.hospitalcrud.dao.model;

import lombok.Data;

import java.time.LocalDate;
@Data

public class Patient {
    private int id;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private Credential credential;

    public Patient(int id, String name, LocalDate dob, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = dob;
        this.phone = phone;

    }
}
