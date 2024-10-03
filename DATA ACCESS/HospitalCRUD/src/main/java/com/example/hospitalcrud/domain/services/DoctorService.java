package com.example.hospitalcrud.domain.services;

import com.example.hospitalcrud.dao.model.Doctor;
import com.example.hospitalcrud.dao.repositories.statics.DoctorRepository;
import com.example.hospitalcrud.domain.model.DoctorUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;
    public DoctorService() {
        this.doctorRepository = new DoctorRepository();
    }

    public List<DoctorUI> getAll() {
        List<Doctor> doctors = doctorRepository.getAll();
        List<DoctorUI> doctorUIs = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorUIs.add(new DoctorUI(doctor.getId(), doctor.getName()));
        }
        return doctorUIs;
    }
}
