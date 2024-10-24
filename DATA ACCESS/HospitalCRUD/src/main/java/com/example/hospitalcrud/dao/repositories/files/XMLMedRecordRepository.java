package com.example.hospitalcrud.dao.repositories.files;

import com.example.hospitalcrud.config.Configuration;
import com.example.hospitalcrud.dao.model.MedRecord;
import com.example.hospitalcrud.dao.model.Medication;
import com.example.hospitalcrud.dao.repositories.MedRecordRepository;
import com.example.hospitalcrud.dao.xml.MedRecordXML;
import com.example.hospitalcrud.dao.xml.MedRecordsXML;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.IntStream;


@Profile("files")
@Repository
public class XMLMedRecordRepository implements MedRecordRepository {

    private final Path medRecordsFilePath;
    private int nextIdPath;

    public XMLMedRecordRepository() {
        medRecordsFilePath = Paths.get(Configuration.getInstance().getProperty("pathMedRecords"));
        nextIdPath = Integer.parseInt(Configuration.getInstance().getProperty("nextIdMedRecord"));
        try {
            if (!Files.exists(medRecordsFilePath)) {
                Files.createDirectories(medRecordsFilePath.getParent());
                initializeXMLFile();
            } else if (Files.size(medRecordsFilePath) == 0) {
                initializeXMLFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize XML file", e);
        }
    }

    @Override
    public List<MedRecord> getAll() {
        try {
            JAXBContext context = JAXBContext.newInstance(MedRecordsXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MedRecordsXML medRecordsList = (MedRecordsXML) unmarshaller.unmarshal(Files.newInputStream(medRecordsFilePath));


            return medRecordsList.getMedRecords().stream()
                    .map(this::convertToMedRecord)
                    .toList();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read medical records from XML", e);
        }
    }
    @Override
    public int add(MedRecord medRecord) {
        try {
            JAXBContext context = JAXBContext.newInstance(MedRecordsXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MedRecordsXML medRecordsList = (MedRecordsXML) unmarshaller.unmarshal(Files.newInputStream(medRecordsFilePath));

            int nextId = getNextId(medRecordsList);
            medRecord.setId(nextId);

            MedRecordXML newMedRecordXML = convertToMedRecordXML(medRecord);
            medRecordsList.getMedRecords().add(newMedRecordXML);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medRecordsList, Files.newOutputStream(medRecordsFilePath));

            return nextId;
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Failed to add medical record", e);
        }
    }

    @Override
    public void update(MedRecord medRecord) {
        try {
            JAXBContext context = JAXBContext.newInstance(MedRecordsXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MedRecordsXML medRecordsList = (MedRecordsXML) unmarshaller.unmarshal(Files.newInputStream(medRecordsFilePath));
            medRecordsList.getMedRecords().set(
                    IntStream.range(0, medRecordsList.getMedRecords().size())
                            .filter(i -> medRecordsList.getMedRecords().get(i).getId() == medRecord.getId())
                            .findFirst()
                            .orElseThrow(),
                    convertToMedRecordXML(medRecord));
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medRecordsList, Files.newOutputStream(medRecordsFilePath));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Failed to update medical record", e);
        }
    }

    @Override
    public void delete(MedRecord medRecord) {
        try {
            JAXBContext context = JAXBContext.newInstance(MedRecordsXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MedRecordsXML medRecordsList = (MedRecordsXML) unmarshaller.unmarshal(Files.newInputStream(medRecordsFilePath));
            medRecordsList.getMedRecords().removeIf(recordXML -> recordXML.getId() == medRecord.getId());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medRecordsList, Files.newOutputStream(medRecordsFilePath));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Failed to delete medical record", e);
        }
    }

    private int getNextId(MedRecordsXML medRecordsList) {
        nextIdPath = medRecordsList.getMedRecords().stream()
                .mapToInt(MedRecordXML::getId)
                .max()
                .orElse(0) + 1;
        updateNextIdInProperties();
        return nextIdPath;
    }

    private MedRecord convertToMedRecord(MedRecordXML xml) {
        return new MedRecord(
                xml.getId(),
                xml.getIdPatient(),
                xml.getDoctor(),
                xml.getDiagnosis(),
                xml.getDate(),
                xml.getMedications().stream()
                        .map(medicationName -> new Medication(0, medicationName, xml.getId()))
                        .toList()
        );
    }

    private MedRecordXML convertToMedRecordXML(MedRecord medRecord) {
        return MedRecordXML.builder()
                .id(medRecord.getId())
                .idPatient(medRecord.getIdPatient())
                .doctor(medRecord.getIdDoctor())
                .diagnosis(medRecord.getDiagnosis())
                .date(medRecord.getDate())
                .medications(medRecord.getMedications().stream()
                        .map(Medication::getMedicationName)
                        .toList())
                .build();
    }

    private void updateNextIdInProperties() {
        Configuration config = Configuration.getInstance();
        config.setProperty("nextIdMedRecord", String.valueOf(nextIdPath));
    }

    private void initializeXMLFile() {
        MedRecordsXML medRecordsXML = new MedRecordsXML();
        medRecordsXML.setMedRecords(new ArrayList<>());

        try {
            JAXBContext context = JAXBContext.newInstance(MedRecordsXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medRecordsXML, Files.newOutputStream(medRecordsFilePath));
        } catch (JAXBException | IOException e) {
            throw new RuntimeException("Failed to initialize XML file", e);
        }
    }
    @Override
    public void delete(int patientId) {
        try {

            JAXBContext context = JAXBContext.newInstance(MedRecordsXML.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            MedRecordsXML medRecordsList = (MedRecordsXML) unmarshaller.unmarshal(Files.newInputStream(medRecordsFilePath));

            List<MedRecordXML> updatedRecords = medRecordsList.getMedRecords().stream()
                    .filter(record -> record.getIdPatient() != patientId)
                    .toList();

            medRecordsList.setMedRecords(new ArrayList<>(updatedRecords));

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(medRecordsList, Files.newOutputStream(medRecordsFilePath));
        } catch (IOException | JAXBException e) {
            throw new RuntimeException("Failed to delete medical records for patient", e);
        }
    }
}