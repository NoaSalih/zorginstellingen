package com.zorginstellingen.controller;

import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.User;
import com.zorginstellingen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.zorginstellingen.global.GlobalService.getUserFromToken;


@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;


    @GetMapping("getAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Patient patient) {
        User user = getUserFromToken();
        patient.setUserId(user.getId());
        try {
            return ResponseEntity.ok(patientService.addPatient(patient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(patientService.updatePatientInfo(patient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(patientService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(patientService.findPatientByPatientId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("findByDoctorId")
    public ResponseEntity<?> findByDoctorId() {
        User user = getUserFromToken();
        try {
            return ResponseEntity.ok(patientService.getAllPatientsByDoctorId(user.getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("activePatientAccount")
    public ResponseEntity<?> activePatientAccount(@RequestBody Patient patient) {
        try {
            return ResponseEntity.ok(patientService.activePatientAccount(patient));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
