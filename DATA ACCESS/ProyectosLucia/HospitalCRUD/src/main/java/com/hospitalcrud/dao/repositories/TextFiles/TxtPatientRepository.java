package com.hospitalcrud.dao.repositories.TextFiles;

import com.hospitalcrud.dao.model.Credential;
import com.hospitalcrud.dao.model.Patient;
import com.hospitalcrud.dao.repositories.PatientRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("this")
public class TxtPatientRepository implements PatientRepository {
    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<Patient>();
        patients.add(new Patient(
                LocalDate.of(2003, 5, 23),
                new Credential("john_smith", "password123", 1),
                1,
                "Paciente de fichero",
                "333-222-111"
        ));
        return patients;
    }

    @Override
    public int add(Patient patient) {
        return 0;
    }

    @Override
    public void update(Patient patientDatabase) {

    }

    @Override
    public void delete(int idDelete, boolean confirm) {

    }
}
