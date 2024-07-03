package com.practo.hospital.management.repository;

import com.practo.hospital.management.model.Appointment;
import com.practo.hospital.management.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndAppointmentTimeBetween(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime);
}
