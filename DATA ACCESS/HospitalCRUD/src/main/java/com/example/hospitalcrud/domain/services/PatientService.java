package com.example.hospitalcrud.domain.services;

import com.example.hospitalcrud.dao.model.Patient;
import com.example.hospitalcrud.dao.repositories.PatientRepository;
import com.example.hospitalcrud.domain.model.PatientUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;

    }

    public List<PatientUI> getPatients() {
        List<Patient> patients = patientRepository.getAll();

        List<PatientUI> patientUIs = new ArrayList<>();
        for (Patient patient : patients) {
            patientUIs.add(new PatientUI(
                    patient.getId(),
                    0, // Otro parámetro que tal vez necesites ajustar
                    patient.getName(),
                    patient.getPhone(),
                    null, null, // Parámetros adicionales
                    patient.getBirthday()));
        }

        return patientUIs;
    }

    public int addPatient(PatientUI patientUI) {
        // No necesitamos pasar el ID desde UI, ya que lo asignaremos en el repositorio
        Patient patient = new Patient(0, patientUI.getName(), patientUI.getPhone(), patientUI.getBirthDate());
        return patientRepository.save(patient);

    }


    public void updatePatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getPhone(), patientUI.getBirthDate());

        patientRepository.update(patient);
    }

    public void delete(int patientId, boolean confirm) {
            patientRepository.delete(patientId,confirm);
    }
}
