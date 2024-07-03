package com.practo.hospital.management.controller;

import com.practo.hospital.management.model.Patient;
import com.practo.hospital.management.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id){
        return patientService.getPatientById(id);
    }

    @PostMapping
    public String registerPatient(@RequestBody Patient patient){
        patientService.registerPatient(patient);
        return "Patient is registered";
    }

    @DeleteMapping("{id}")
    public String deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return "Patient is deleted from the system";
    }
}