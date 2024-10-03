package com.example.hospitalcrud.dao.repositories;

import com.example.hospitalcrud.dao.model.Patient;

import java.util.List;

public interface PatientDAO {

    List<Patient> getAll();
    int save(Patient patient);
    void delete(int id, boolean confirm);
    void update(Patient patient);
}
