package com.practo.hospital.management.controller;

import com.practo.hospital.management.model.Prescription;
import com.practo.hospital.management.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("prescription")
@CrossOrigin
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping
    public List<Prescription> getAllPrescription(){
        return prescriptionService.getAllPrescription();
    }

    @GetMapping("{doctorId}")
    public ResponseEntity<?> getPrescriptionByDoctorId(@PathVariable Long doctorId){
        try{
            List<Prescription> prescriptions = prescriptionService.getPrescriptionByDoctorId(doctorId);
            return new ResponseEntity<>(prescriptions, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{appointmentId}")
    public ResponseEntity<?> addPrescription(@PathVariable Long appointmentId, @RequestBody String remarks){
        try {
            Prescription prescription = prescriptionService.addPrescription(appointmentId, remarks);
            return new ResponseEntity<>(prescription, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePrescription(@PathVariable Long id, @RequestBody String remarks){
        try{
            Prescription prescription = prescriptionService.updatePrescription(id, remarks);
            return new ResponseEntity<>(prescription, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removePrescription(@PathVariable Long id){
        try{
            prescriptionService.removePrescription(id);
            return new ResponseEntity<>("Prescription deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
