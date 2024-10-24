package com.example.hospitalcrud.dao.repositories.files;
import com.example.hospitalcrud.config.Configuration;
import com.example.hospitalcrud.dao.mappers.PatientRowMapper;
import com.example.hospitalcrud.dao.model.Patient;
import com.example.hospitalcrud.dao.repositories.PatientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.io.*;
import java.nio.file.*;

@Profile("files")
@Repository
public class TxtPatientRepository implements PatientRepository {
    private final String patientsFilePath;
    private final String medRecordsFilePath;
    private final PatientRowMapper patientRowMapper;
    private int nextId;

    public TxtPatientRepository(PatientRowMapper patientRowMapper) {
        Configuration config = Configuration.getInstance();
        this.patientsFilePath = config.getProperty("pathPatients");
        this.medRecordsFilePath = config.getProperty("pathMedRecords");
        this.patientRowMapper = patientRowMapper;
        nextId = Integer.parseInt(config.getProperty("nextIdPatient"));


    }

    private List<String> readFile() {
        Path file = Paths.get(patientsFilePath);
        try {
            //TODO: Change for newBufferedReader
            return Files.readAllLines(file);
        } catch (IOException e) {
            throw new RuntimeException("Error reading patients file", e);
        }
    }

    private List<Patient> mapFile(List<String> lines) {
        List<Patient> patients = lines.stream()
                .map(patientRowMapper::fromString)
                .toList();
        if (!patients.isEmpty()) {
            nextId = patients.stream()
                    .mapToInt(Patient::getId)
                    .max()
                    .getAsInt() + 1;
            updateNextIdInProperties();
        }
        return patients;
    }

    public List<Patient> getAll() {
        List<String> lines = readFile();
        return mapFile(lines);
    }

    public void update(Patient patient) {
        List<Patient> patients = getAll();
        List<String> updatedLines = patients.stream()
                .map(p -> p.getId() == patient.getId() ? patient : p)
                .map(Patient::toString)
                .toList();

        writeFile(updatedLines);
    }

    public void delete(int patientId, boolean confirm) {
        List<Patient> patients = getAll();
        List<String> updatedLines = patients.stream()
                .filter(p -> p.getId() != patientId)
                .map(Patient::toString)
                .toList();

        writeFile(updatedLines);

        // Aqui se eliminan los medRecords del paciente
    }

    private void writeFile(List<String> lines) {
        Path file = Paths.get(patientsFilePath);
        try {
            Files.write(file, lines);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to patients file", e);
        }
    }

    public int add(Patient patient) {
        //TODO: Leer con un getProperty
        patient.setId(nextId);
        nextId++;

        Path file = Paths.get(patientsFilePath);
        try {
            Files.write(file, (patient + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            updateNextIdInProperties();
        } catch (IOException e) {
            throw new RuntimeException("Error saving patient", e);
        }
        return patient.getId();
    }

    private void updateNextIdInProperties() {
        Configuration config = Configuration.getInstance();
        config.setProperty("nextIdPatient", String.valueOf(nextId));
    }



}










































