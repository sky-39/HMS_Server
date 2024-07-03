package com.practo.hospital.management.controller;

import com.practo.hospital.management.model.Appointment;
import com.practo.hospital.management.model.Doctor;
import com.practo.hospital.management.model.Patient;
import com.practo.hospital.management.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@CrossOrigin
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAllAppointments();
    }

    @GetMapping("{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id){
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Appointment appointment) throws Exception {
        try{
            Patient patient = appointment.getPatient();
            Doctor doctor = appointment.getDoctor();
            appointmentService.scheduleAppointment(patient.getId(), doctor.getId(), appointment.getAppointmentTime());
            return new ResponseEntity<>(appointment, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) throws Exception{
        try{
            appointmentService.rescheduleAppointment(appointment);
            return new ResponseEntity<>(appointment,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public String cancelAppointment(@PathVariable Long id){
        appointmentService.cancelAppointment(id);
        return "Appointment Cancelled";
    }
}
