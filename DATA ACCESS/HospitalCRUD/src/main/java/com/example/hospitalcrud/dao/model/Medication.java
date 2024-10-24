package com.example.hospitalcrud.dao.model;

import lombok.*;

import javax.xml.bind.annotation.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@XmlAccessorType(XmlAccessType.FIELD)
public class Medication {

    @XmlTransient
    private int id;
    @XmlValue
    private String medicationName;
   @XmlTransient
    private int medRecordId;
}
