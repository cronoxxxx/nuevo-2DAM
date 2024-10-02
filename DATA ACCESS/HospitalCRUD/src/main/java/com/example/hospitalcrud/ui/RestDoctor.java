package com.example.hospitalcrud.ui;

import com.example.hospitalcrud.domain.model.DoctorUI;
import com.example.hospitalcrud.domain.services.DoctorService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestDoctor {

    private DoctorService doctorService;
    //
    public RestDoctor(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/doctors")
    public List<DoctorUI> index() {
        return doctorService.getAll();
    }

}

