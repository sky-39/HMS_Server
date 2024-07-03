package com.practo.hospital.management.repository;

import com.practo.hospital.management.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
