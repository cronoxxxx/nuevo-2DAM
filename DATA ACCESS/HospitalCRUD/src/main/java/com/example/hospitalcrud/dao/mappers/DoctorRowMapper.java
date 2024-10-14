package com.example.hospitalcrud.dao.mappers;

import com.example.hospitalcrud.dao.model.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorRowMapper {
    public Doctor fromString(String s) {
        String[] parts = s.split(";");
        return new Doctor(
                Integer.parseInt(parts[0].trim()),
                parts[1].trim(),
                parts[2].trim()
        );
    }
}
