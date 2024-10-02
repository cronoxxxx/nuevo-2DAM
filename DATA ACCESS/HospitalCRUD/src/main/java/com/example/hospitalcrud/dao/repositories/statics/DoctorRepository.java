package com.example.hospitalcrud.dao.repositories.statics;

import com.example.hospitalcrud.dao.model.Doctor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter@Setter
public class DoctorRepository {
    private List<Doctor> doctors;

    public DoctorRepository() {
        doctors = new ArrayList<>();
        doctors = Arrays.asList(
                new Doctor(1, "Doctor1"),
                new Doctor(2, "Doctor2"),
                new Doctor(3, "Doctor3")
        );
    }

}
