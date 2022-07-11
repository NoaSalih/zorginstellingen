package com.zorginstellingen.service;

import com.zorginstellingen.enums.AppointmentStatus;
import com.zorginstellingen.exception.EmptyException;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Appointment;
import com.zorginstellingen.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;

    /**
     * for ADMIN to check alle doctors en alle appointments
     *
     * @return
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAllAppointmentsByDoctorId(Long userId) {
        return appointmentRepository.findByDoctorId(userId);
    }

    /**
     * @param patientId
     * @return
     */
    public List<Appointment> getAllAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }


    /**
     * @param appointment
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Appointment applyForAppointment(Appointment appointment) throws EmptyException, NotFoundException {
        if (appointment.getPatientId() == null) throw new EmptyException("Please mention patient id");
        if (appointment.getDoctorId() == null) throw new EmptyException("Please mention doctor id");
        if (appointment.getAppointmentDescription() == null)
            throw new EmptyException("Please mention appointment description");
        appointment.setUser(userService.findUserById(appointment.getDoctorId()));
        appointment.setPatient(patientService.findPatientByPatientId(appointment.getPatientId()));
        appointment.setAppointmentStatus(AppointmentStatus.INPROGRESS);
        return appointmentRepository.save(appointment);
    }

    /**
     * @param appointment
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Appointment updateAppointment(Appointment appointment) throws EmptyException, NotFoundException {
        Appointment findApp = appointmentRepository.findById(appointment.getId()).orElseThrow(() -> new NotFoundException("Appointment not found"));
        if (appointment.getDoctorId() != null) findApp.setUser(userService.findUserById(appointment.getDoctorId()));
        if (appointment.getPatientId() != null)
            findApp.setPatient(patientService.findPatientByPatientId(appointment.getPatientId()));
        if (appointment.getAppointmentDescription() != null)
            findApp.setAppointmentDescription(appointment.getAppointmentDescription());
        return appointmentRepository.save(findApp);
    }


    public Appointment deleteAppointment(Long id) throws EmptyException, NotFoundException {
        Appointment findApp = appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Appointment not found"));
        appointmentRepository.delete(findApp);
        return findApp;
    }


    public Appointment findAppointmentById(Long id) throws NotFoundException {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Record not found"));
        return appointment;
    }


}
