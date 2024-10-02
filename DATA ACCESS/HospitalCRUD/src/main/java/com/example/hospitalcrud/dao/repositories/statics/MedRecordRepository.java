package com.example.hospitalcrud.dao.repositories.statics;

import com.example.hospitalcrud.dao.model.MedRecord;
import com.example.hospitalcrud.dao.model.Medication;
import com.example.hospitalcrud.dao.repositories.MedRecordDAO;
import com.example.hospitalcrud.domain.model.MedRecordUI;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedRecordRepository implements MedRecordDAO {
    private List<MedRecord> medRecords;
    private int lastId;

    public MedRecordRepository() {

        medRecords = new ArrayList<>();

            medRecords.add(new MedRecord(1, 1, 67890, "Hipertensión arterial", LocalDate.of(2022, 1, 10), Arrays.asList(
                    new Medication(1, "Amlodipino", 1),
                    new Medication(2, "Hidroclorotiazida", 1)
            )));

            medRecords.add(new MedRecord(2, 1, 78901, "Diabetes tipo 2", LocalDate.of(2022, 2, 20), Arrays.asList(
                    new Medication(3, "Metformina", 2),
                    new Medication(4, "Glipizida", 2)
            )));

            medRecords.add(new MedRecord(3, 1, 89012, "Asma bronquial", LocalDate.of(2022, 3, 15), Arrays.asList(
                    new Medication(5, "Salbutamol", 3),
                    new Medication(6, "Fluticasona", 3)
            )));

            medRecords.add(new MedRecord(4, 2, 90123, "Infección respiratoria", LocalDate.of(2022, 4, 10), Arrays.asList(
                    new Medication(7, "Amoxicilina", 4),
                    new Medication(8, "Clavulanato", 4)
            )));

            medRecords.add(new MedRecord(5, 3, 12345, "Lesión deportiva", LocalDate.of(2022, 5, 25), Arrays.asList(
                    new Medication(9, "Ibuprofeno", 5),
                    new Medication(10, "Paracetamol", 5)
            )));

            medRecords.add(new MedRecord(6, 4, 23456, "Enfermedad cardiovascular", LocalDate.of(2022, 6, 15), Arrays.asList(
                    new Medication(11, "Atorvastatina", 6),
                    new Medication(12, "Aspirina", 6)
            )));

            medRecords.add(new MedRecord(7, 5, 34567, "Cáncer de mama", LocalDate.of(2022, 7, 10), Arrays.asList(
                    new Medication(13, "Tamoxifeno", 7),
                    new Medication(14, "Anastrozol", 7)
            )));

            medRecords.add(new MedRecord(8, 4, 45678, "Enfermedad renal crónica", LocalDate.of(2022, 8, 25), Arrays.asList(
                    new Medication(15, "Epoetina alfa", 8),
                    new Medication(16, "Ferrosa", 8)
            )));

            medRecords.add(new MedRecord(9, 5, 56789, "Enfermedad pulmonar obstructiva crónica", LocalDate.of(2022, 9, 15), Arrays.asList(
                    new Medication(17, "Salbutamol", 9),
                    new Medication(18, "Tiotropio", 9)
            )));

            medRecords.add(new MedRecord(10, 5, 67890, "Enfermedad de Alzheimer", LocalDate.of(2022, 10, 10), Arrays.asList(
                    new Medication(19, "Donepezilo", 10),
                    new Medication(20, "Rivastigmina", 10)
            )));
        lastId = 0;
    }

    public List<MedRecord> getAll() {
        return medRecords;
    }

    public int addMedRecord(MedRecordUI medRecordUI) {
//        lastId++;
//        MedRecord medRecord = MedRecord.builder()
//                .id(lastId)
//                .idPatient(medRecordUI.getIdPatient())
//                .idDoctor(medRecordUI.getIdDoctor())
//                .diagnosis(medRecordUI.getDescription())
//                .date(LocalDate.parse(medRecordUI.getDate()))
//                .medications(convertToMedications(medRecordUI.getMedications(), lastId))
//                .build();
//        medRecords.add(medRecord);
        System.out.println("Paciente anadido");
        return 0;
    }

    public void updateMedRecord(MedRecordUI medRecordUI) {
//        for (MedRecord medRecord : medRecords) {
//            if (medRecord.getId() == medRecordUI.getId()) {
//                medRecord.setIdPatient(medRecordUI.getIdPatient());
//                medRecord.setIdDoctor(medRecordUI.getIdDoctor());
//                medRecord.setDiagnosis(medRecordUI.getDescription());
//                medRecord.setDate(LocalDate.parse(medRecordUI.getDate()));
//                medRecord.setMedications(convertToMedications(medRecordUI.getMedications(), medRecord.getId()));
//                break;
//            }
//        }
        System.out.println("Paciente actualizado");
    }

    public void deleteMedRecord(int id) {
//        medRecords.removeIf(medRecord -> medRecord.getId() == id);
    }

//    private List<Medication> convertToMedications(List<String> medicationNames, int medRecordId) {
//        List<Medication> medications = new ArrayList<>();
//        for (String name : medicationNames) {
//            medications.add(new Medication(0, name, medRecordId));
//        }
//        return medications;
//    }
}