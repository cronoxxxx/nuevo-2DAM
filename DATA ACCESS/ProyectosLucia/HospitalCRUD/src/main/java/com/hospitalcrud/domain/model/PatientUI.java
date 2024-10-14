package com.hospitalcrud.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientUI {

        private int id;
        private String name;
        private LocalDate birthDate;
        private String phone;
        private int paid;
        private String userName;
        private String password;



    public PatientUI(int id, String name, LocalDate birthDate, String phone, int paid, String userName, String password) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.paid = paid;
        this.userName = userName;
        this.password = password;
    }


}

