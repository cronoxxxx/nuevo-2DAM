package com.hospitalcrud.dao.model;

import lombok.*;

import javax.xml.bind.annotation.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Medication {

    private int id;
    private String medicationName;
    private int medRecordId;
}




