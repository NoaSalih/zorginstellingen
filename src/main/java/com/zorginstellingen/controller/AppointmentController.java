package com.zorginstellingen.controller;

import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Appointment;
import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.User;
import com.zorginstellingen.service.AppointmentService;
import com.zorginstellingen.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.zorginstellingen.global.GlobalService.getUserFromToken;


@RestController
@RequestMapping("/appointment/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;


    @GetMapping("getAll")
    public ResponseEntity<?> findAll(){
        List<Appointment> appointments = appointmentService.getAllAppointments();
        for (Appointment app : appointments) {
            app.setDoctorId(app.getUser().getId());
            app.setPatientId(app.getPatient().getId());
            app.setDoctorName(app.getUser().getUsername());
            app.setPatientName(app.getPatient().getPatientName());
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("getAllByPatient")
    public ResponseEntity<?> getAllByPatient(){
        User user = getUserFromToken();
        Patient patient = patientService.findPatientByUsername(user.getUsername());
        List<Appointment> appointments = appointmentService.getAllAppointmentsByPatientId(patient.getId());
        for (Appointment app : appointments) {
            app.setDoctorId(app.getUser().getId());
            app.setPatientId(app.getPatient().getId());
            app.setDoctorName(app.getUser().getUsername());
            app.setPatientName(app.getPatient().getPatientName());
        }
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("getAllAppointmentsByDoctorId")
    public ResponseEntity<?> getAllAppointmentsByDoctorId(){
        User user = getUserFromToken();
        List<Appointment> appointments = appointmentService.getAllAppointmentsByDoctorId(user.getId());
        for (Appointment app : appointments) {
            app.setDoctorId(app.getUser().getId());
            app.setPatientId(app.getPatient().getId());
        }
        return ResponseEntity.ok(appointments);
    }


    @GetMapping("getAllPatientAppointments")
    public ResponseEntity<?> findAllByPatientId(){
        User user = getUserFromToken();
        return ResponseEntity.ok(appointmentService.getAllAppointmentsByPatientId(user.getId()));
    }


    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Appointment appointment) throws NotFoundException {
        User user = getUserFromToken();
        Patient patient = patientService.findPatientByUsername(user.getUsername());
        appointment.setPatientId(patient.getId());
        appointment.setDoctorId(patient.getUser().getId());
        try{
            return ResponseEntity.ok(appointmentService.applyForAppointment(appointment));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody Appointment appointment){
        User user = getUserFromToken();
        try{
            return ResponseEntity.ok(appointmentService.updateAppointment(appointment));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable( name= "id") Long id){
        try{
            return ResponseEntity.ok(appointmentService.deleteAppointment(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findById(@PathVariable( name= "id") Long id){
        try{
            return ResponseEntity.ok(appointmentService.findAppointmentById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





}
