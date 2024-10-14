package com.example.hospitalcrud.dao.repositories;

import com.example.hospitalcrud.dao.model.MedRecord;
import com.example.hospitalcrud.domain.model.MedRecordUI;

import java.util.List;

public interface MedRecordRepository {

    List<MedRecord> getAll();

    int add(MedRecordUI medRecordUI);

    void update(MedRecordUI medRecordUI);

    void deleteMedRecord(int id);
}
