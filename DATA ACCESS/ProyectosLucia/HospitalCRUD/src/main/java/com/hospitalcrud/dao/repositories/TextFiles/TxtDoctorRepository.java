package com.hospitalcrud.dao.repositories.TextFiles;

import com.hospitalcrud.dao.model.Doctor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("this")
public class TxtDoctorRepository implements com.hospitalcrud.dao.repositories.DoctorRepository {
    @Override
    public List<Doctor> getAll() {
        return List.of();
    }
}
