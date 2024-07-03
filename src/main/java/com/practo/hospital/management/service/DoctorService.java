package com.practo.hospital.management.service;

import com.practo.hospital.management.model.Doctor;
import com.practo.hospital.management.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id){
        return doctorRepository.findById(id);
    }

    public void registerDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }
}
