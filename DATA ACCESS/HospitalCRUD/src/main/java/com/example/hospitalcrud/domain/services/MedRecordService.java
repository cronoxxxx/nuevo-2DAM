package com.example.hospitalcrud.domain.services;

import com.example.hospitalcrud.dao.model.MedRecord;
import com.example.hospitalcrud.dao.model.Medication;
import com.example.hospitalcrud.dao.repositories.MedRecordRepository;
import com.example.hospitalcrud.dao.repositories.statics.StaticMedRecordRepository;
import com.example.hospitalcrud.domain.model.MedRecordUI;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MedRecordService {
    private final MedRecordRepository medRecordRepository;

    public MedRecordService(MedRecordRepository medRecordRepository) {
        this.medRecordRepository = medRecordRepository;
    }

    public void update(MedRecordUI medRecordUI) {
        medRecordRepository.update(medRecordUI);
    }

    public List<MedRecordUI> getAll(int idPatient) {
        List<MedRecord> allMedRecords = medRecordRepository.getAll();
        return allMedRecords.stream()
                .filter(medRecord -> medRecord.getIdPatient() == idPatient)
                .map(this::convertToMedRecordUI)
                .toList();
    }

    public int add(MedRecordUI medRecordUI) {
        return medRecordRepository.add(medRecordUI);
    }

    public void delete(int id) {
        medRecordRepository.deleteMedRecord(id);
    }

    private MedRecordUI convertToMedRecordUI(MedRecord medRecord) {
        return MedRecordUI.builder()
                .id(medRecord.getId())
                .description(medRecord.getDiagnosis())
                .date(medRecord.getDate().toString())
                .idPatient(medRecord.getIdPatient())
                .idDoctor(medRecord.getIdDoctor())
                .medications(medRecord.getMedications().stream()
                        .map(Medication::getMedicationName)
                        .toList())
                .build();
    }
}