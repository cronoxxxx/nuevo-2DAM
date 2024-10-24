package com.example.hospitalcrud.dao.repositories;

import com.example.hospitalcrud.dao.model.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PatientRepository {

    List<Patient> getAll();
    int add(Patient patient);
    void delete(int id, boolean confirm);
    void update(Patient patient);
}
