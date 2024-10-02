package com.example.hospitalcrud.dao.repositories.statics;

import com.example.hospitalcrud.dao.model.Patient;
import com.example.hospitalcrud.dao.repositories.PatientDAO;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
public class PatientRepository implements PatientDAO {
    private List<Patient> patients;
    private int lastId;

    public PatientRepository() {
        this.patients = new ArrayList<>();
        this.patients.add(new Patient(1, "John", "Doe", LocalDate.of(2000, 1, 1)));
        this.patients.add(new Patient(2, "Juan", "Perez", LocalDate.of(1990, 4, 12)));
        this.patients.add(new Patient(3, "Pedro", "Garcia", LocalDate.of(1995, 5, 1)));
        this.patients.add(new Patient(4, "Maria", "Rodriguez", LocalDate.of(1980, 1, 15)));
        this.patients.add(new Patient(5, "Ana", "Gonzalez", LocalDate.of(1975, 6, 20)));
        this.patients.add(new Patient(6, "Luis", "Sanchez", LocalDate.of(1960, 7, 25)));
        // El último ID
        this.lastId = patients.get(patients.size() - 1).getId();
    }
    public int addPatient(Patient patient) {
        // Incrementamos el último ID y lo asignamos al nuevo paciente
//        lastId++;
//        patient.setId(lastId);
//        patients.add(patient);
//        return patient.getId();
        System.out.println("Paciente anadido");
        return 0;
    }

    public List<Patient> getAll() {
        return patients;
    }

    public void deletePatient(int patientId, boolean confirm) {
        //patients.removeIf(patient -> patient.getId() == patientId);
        System.out.println("Patient deleted: " + patientId);
    }

    public void updatePatient(Patient patient) {
        System.out.println("Paciente actualizado");
//        for (Patient existingPatient : patients) {
//            if (existingPatient.getId() == patient.getId()) {
//                existingPatient.setName(patient.getName());
//                existingPatient.setPhone(patient.getPhone());
//                existingPatient.setBirthday(patient.getBirthday());
//                break;
//            }
//        }
    }
}
