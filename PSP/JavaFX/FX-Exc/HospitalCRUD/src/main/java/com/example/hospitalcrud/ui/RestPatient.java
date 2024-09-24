package com.example.hospitalcrud.ui;

import com.example.hospitalcrud.domain.services.PatientService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestPatient {
    private final PatientService patientService;

    public RestPatient(PatientService patientService) {
        this.patientService = patientService;
    }

    public List<Patient> getPatients() {
        return patientService.getPatients();
    }
}
