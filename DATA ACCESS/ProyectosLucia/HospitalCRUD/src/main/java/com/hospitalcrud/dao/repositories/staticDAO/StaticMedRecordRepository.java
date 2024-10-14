package com.hospitalcrud.dao.repositories.staticDAO;

import com.hospitalcrud.dao.model.MedRecord;
import com.hospitalcrud.dao.model.Medication;
import com.hospitalcrud.dao.repositories.MedRecordRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("static")
public class StaticMedRecordRepository implements MedRecordRepository {

    private static List<MedRecord> medRecordList=new ArrayList<>();


    public List<MedRecord> getAll(int idPatient) {
        ArrayList<Medication> medicationsList=new ArrayList<>();
        medicationsList.add(new Medication(1,"Paracetamos",1));
        medRecordList.add(new MedRecord(1,1,1,"Cuentitis Aguditis", LocalDate.now(), medicationsList));
        return medRecordList;
    }

    public int add(MedRecord medRecord){

        return 1;
    }

    public void delete(int id) {

    }

    public void update(MedRecord medRecord) {

    }
}
