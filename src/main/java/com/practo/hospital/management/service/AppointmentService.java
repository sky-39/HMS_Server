package com.practo.hospital.management.service;

import com.practo.hospital.management.model.Appointment;
import com.practo.hospital.management.model.Doctor;
import com.practo.hospital.management.model.Patient;
import com.practo.hospital.management.repository.AppointmentRepository;
import com.practo.hospital.management.repository.DoctorRepository;
import com.practo.hospital.management.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    public void scheduleAppointment(Long docId, Long patId, LocalDateTime appointmentTime) throws Exception{
        Patient patient = patientRepository.findById(patId).orElseThrow(()-> new RuntimeException("Patient Not found"));
        Doctor doctor = doctorRepository.findById(docId).orElseThrow(()-> new RuntimeException("Doctor Not found"));

        List<Appointment> appointments = appointmentRepository.findByDoctorAndAppointmentTimeBetween(doctor, appointmentTime.minusMinutes(30), appointmentTime.plusMinutes(30));
        if(!appointments.isEmpty()){
            throw new Exception("Doctor is not available at this time");
        }
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(appointmentTime);

        appointmentRepository.save(appointment);
    }

    public void rescheduleAppointment(Appointment appointment) throws Exception{
        List<Appointment> appointments = appointmentRepository.findByDoctorAndAppointmentTimeBetween(appointment.getDoctor(), appointment.getAppointmentTime().minusMinutes(30), appointment.getAppointmentTime().plusMinutes(30));
        if(!appointments.isEmpty()){
            throw new Exception("Doctor is not available at this time");
        }

        appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id){
        appointmentRepository.deleteById(id);
    }
}
