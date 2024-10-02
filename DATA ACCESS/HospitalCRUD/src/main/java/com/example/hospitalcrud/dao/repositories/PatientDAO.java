package com.example.hospitalcrud.dao.repositories;

import com.example.hospitalcrud.dao.model.Patient;

import java.util.List;

public interface PatientDAO {

    List<Patient> getAll();
    int addPatient(Patient patient);
    void deletePatient(int id, boolean confirm);
    void updatePatient(Patient patient);
}
