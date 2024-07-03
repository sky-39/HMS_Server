package com.practo.hospital.management.repository;

import com.practo.hospital.management.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    @Query("SELECT p from Prescription p WHERE p.appointment.doctor.id = :doctorId")
    List<Prescription> findByDoctorId(@Param("doctorId") Long doctorId);
}
