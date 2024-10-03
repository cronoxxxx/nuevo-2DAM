package com.example.hospitalcrud.dao.repositories.statics;

import com.example.hospitalcrud.dao.model.Doctor;
import com.example.hospitalcrud.dao.repositories.DoctorDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoctorRepository implements DoctorDAO {
    private List<Doctor> doctors;

    public DoctorRepository() {
        doctors = new ArrayList<>();
        doctors = Arrays.asList(
                new Doctor(1, "Doctor1"),
                new Doctor(2, "Doctor2"),
                new Doctor(3, "Doctor3")
        );
    }
    public List<Doctor> getAll() {
        return doctors;
    }

}
