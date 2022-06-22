package zorginstellingen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zorginstellingen.model.Appointment;
import zorginstellingen.model.User;
import zorginstellingen.service.AppointmentService;

import java.util.List;

import static zorginstellingen.global.GlobalService.getUserFromToken;

@RestController
@RequestMapping("/appointment/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * Called by only ADMIN
     * @return
     */
    @GetMapping("getAll")
    public ResponseEntity<?> findAll(){
        List<Appointment> appointments = appointmentService.getAllAppointments();
        for (Appointment app : appointments) {
            app.setDoctorId(app.getUser().getId());
            app.setPatientId(app.getPatient().getId());
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
    public ResponseEntity<?> create(@RequestBody Appointment appointment){
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
