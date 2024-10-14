package com.example.hospitalcrud.dao.repositories.textfiles;
import com.example.hospitalcrud.config.Configuration;
import com.example.hospitalcrud.dao.mappers.PatientRowMapper;
import com.example.hospitalcrud.dao.model.Patient;
import com.example.hospitalcrud.dao.repositories.PatientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.io.*;
import java.nio.file.*;

@Profile("this")
@Repository
public class TxtPatientRepository implements PatientRepository {
    private final String patientsFilePath;
    private final PatientRowMapper patientRowMapper;

    public TxtPatientRepository(PatientRowMapper patientRowMapper) {
        this.patientsFilePath = Configuration.getInstance().getProperty("pathPatients");
        this.patientRowMapper = patientRowMapper;
        //Calculate the next id with the attribute nextID in properties.txt, read the file, set the property in the properties.txt
        updateNextId();
    }

    private void updateNextId() {
        List<Patient> patients = getAll();
        int maxId = patients.stream()
                .mapToInt(Patient::getId)
                .max()
                .orElse(0);

        int nextId = maxId + 1;
        Configuration.getInstance().setProperty("nextId", String.valueOf(nextId));
    }


    public List<Patient> getAll() {
        List<Patient> patients;
        Path file = Paths.get(patientsFilePath);
        try {
            List<String> lines = Files.readAllLines(file);

            patients = lines.stream()
                    .map(patientRowMapper::fromString)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Error reading patients file", e);
        }
        return patients;
    }
    public void update(Patient patient) {
        List<Patient> patients = getAll();
        List<String> updatedLines = patients.stream()
                .map(p -> p.getId() == patient.getId() ? patient : p)
                .map(Patient::toString)
                .toList();

        Path file = Paths.get(patientsFilePath);
        try {
            Files.write(file, updatedLines);
        } catch (IOException e) {
            throw new RuntimeException("Error updating patient", e);
        }
    }


    public void delete(int patientId, boolean confirm) {
        List<Patient> patients = getAll();
        List<String> updatedLines = patients.stream()
                .filter(p -> p.getId() != patientId)
                .map(Patient::toString)
                .toList();

        Path file = Paths.get(patientsFilePath);
        try {
            Files.write(file, updatedLines);
        } catch (IOException e) {
            throw new RuntimeException("Error deleting patient", e);
        }
    }

    public int save(Patient patient) {
        List<Patient> patients = getAll();
        int newId = patients.isEmpty() ? 1 : patients.get(patients.size() - 1).getId() + 1;
        patient.setId(newId);

        Path file = Paths.get(patientsFilePath);
        try {
            Files.write(file, (patient + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Error saving patient", e);
        }
        return newId;
    }
}










































/*
* public Patient get(int id) {
        return getAll().stream()
                .filter(patient -> patient.getId() == id)
                .findFirst()
                .orElse(null);
    }




* */