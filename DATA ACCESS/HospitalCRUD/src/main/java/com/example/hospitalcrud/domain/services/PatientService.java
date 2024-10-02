package com.example.hospitalcrud.domain.services;

import com.example.hospitalcrud.dao.model.Patient;
import com.example.hospitalcrud.dao.repositories.statics.PatientRepository;
import com.example.hospitalcrud.domain.model.PatientUI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService() {
        this.patientRepository = new PatientRepository();
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
        System.out.println("Pacientes: " + patientUIs);
        return patientUIs;
    }

    public int addPatient(PatientUI patientUI) {
        // No necesitamos pasar el ID desde UI, ya que lo asignaremos en el repositorio
        Patient patient = new Patient(0, patientUI.getName(), patientUI.getPhone(), patientUI.getBirthday());
        return patientRepository.addPatient(patient);

    }


    public void updatePatient(PatientUI patientUI) {
        Patient patient = new Patient(patientUI.getId(), patientUI.getName(), patientUI.getPhone(), patientUI.getBirthday());

        patientRepository.updatePatient(patient);
    }

    public void delete(int patientId, boolean confirm) {
            patientRepository.deletePatient(patientId,confirm);
    }
}
