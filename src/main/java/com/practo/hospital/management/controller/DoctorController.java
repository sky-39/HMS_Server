package com.practo.hospital.management.controller;

import com.practo.hospital.management.model.Doctor;
import com.practo.hospital.management.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("{id}")
    public Optional<Doctor> getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @PostMapping
    public String addDoctor(@RequestBody Doctor doctor){
        doctorService.registerDoctor(doctor);
        return "Doctor has been added to the system";
    }

    @DeleteMapping("{id}")
    public String deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return "Doctor has been deleted from system";
    }
}
