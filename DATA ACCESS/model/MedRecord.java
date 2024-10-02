package com.hospitalcrud.dao.model;

import com.spring3.dao.mappers.LocalDateAdapter;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MedRecord {
        private int id;
        private int idPatient;
        private int idDoctor;
        private String diagnosis;
        private LocalDate date;
        private List<Medication> medications;
}

