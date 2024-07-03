package com.practo.hospital.management.service;

import com.practo.hospital.management.model.Appointment;
import com.practo.hospital.management.model.Prescription;
import com.practo.hospital.management.repository.AppointmentRepository;
import com.practo.hospital.management.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Prescription> getAllPrescription(){
        return prescriptionRepository.findAll();
    }

    public Optional<Prescription> getPrescriptionById(Long id){
        return prescriptionRepository.findById(id);
    }

    public Prescription addPrescription(Long appointmentId, String remarks) throws Exception{
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(()-> new Exception("Appointment not found"));

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setRemarks(remarks);
        prescriptionRepository.save(prescription);
        return prescription;
    }

    public Prescription updatePrescription(Long id, String remarks) throws Exception{
        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(()->new Exception("Prescription Not found"));
        prescription.setRemarks(remarks);
        prescriptionRepository.save(prescription);
        return prescription;
    }

    public void removePrescription(Long id) throws Exception{
        Prescription prescription = prescriptionRepository.findById(id).orElseThrow(()-> new Exception("Prescription Not found"));
        prescriptionRepository.delete(prescription);
    }

    public List<Prescription> getPrescriptionByDoctorId(Long doctorId) {
        return prescriptionRepository.findByDoctorId(doctorId);
    }
}