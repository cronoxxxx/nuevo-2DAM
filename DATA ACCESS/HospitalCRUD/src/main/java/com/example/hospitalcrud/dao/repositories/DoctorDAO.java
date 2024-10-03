package com.example.hospitalcrud.dao.repositories;

import com.example.hospitalcrud.dao.model.Doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getAll();
}
