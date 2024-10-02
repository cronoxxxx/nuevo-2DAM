package com.example.hospitalcrud.dao.repositories;

import com.example.hospitalcrud.dao.model.MedRecord;
import com.example.hospitalcrud.domain.model.MedRecordUI;

import java.util.List;

public interface MedRecordDAO {

    public List<MedRecord> getAll();

    public int addMedRecord(MedRecordUI medRecordUI);

    public void updateMedRecord(MedRecordUI medRecordUI);

    public void deleteMedRecord(int id);
}
