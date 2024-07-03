package com.practo.hospital.management.service;

import com.practo.hospital.management.model.Patient;
import com.practo.hospital.management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById (Long id){
        return patientRepository.findById(id);
    }

    public void registerPatient(Patient patient){
        patientRepository.save(patient);
    }

    public void deletePatient(Long id){
        patientRepository.deleteById(id);
    }
}
