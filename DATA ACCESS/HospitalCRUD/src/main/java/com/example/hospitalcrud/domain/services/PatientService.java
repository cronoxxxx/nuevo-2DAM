package com.example.hospitalcrud.domain.services;

import com.example.hospitalcrud.dao.model.Patient;
import com.example.hospitalcrud.dao.repositories.MedRecordRepository;
import com.example.hospitalcrud.dao.repositories.PatientRepository;
import com.example.hospitalcrud.domain.model.PatientUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final MedRecordRepository medRecordRepository;

    public PatientService(PatientRepository patientRepository, MedRecordRepository medRecordRepository) {
        this.patientRepository = patientRepository;
        this.medRecordRepository = medRecordRepository;
    }

    public List<PatientUI> getPatients() {
        List<Patient> patients = patientRepository.getAll();

        List<PatientUI> patientUIs = new ArrayList<>();
        for (Patient patient : patients) {
            patientUIs.add(new PatientUI(
                    patient.getId(),
                    0, // Adjust this parameter as needed
                    patient.getName(),
                    patient.getPhone(),
                    null, null, // Additional parameters
                    patient.getBirthday()));
        }

        return patientUIs;
    }

    public int addPatient(PatientUI patientUI) {
        // ID is assigned in the repository
        Patient patient = new Patient(0, patientUI.getName(), patientUI.getPhone(), patientUI.getBirthDate());
        return patientRepository.add(patient);
    }

    public void updatePatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getPhone(), patientUI.getBirthDate());
        patientRepository.update(patient);
    }

    public void delete(int patientId, boolean confirm) {
        medRecordRepository.delete(patientId);
        patientRepository.delete(patientId, confirm);
    }
}
